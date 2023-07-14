package com.aquakloud.ECommerce.service;

import com.aquakloud.ECommerce.dto.ResponseDTO;
import com.aquakloud.ECommerce.dto.user.SignInDTO;
import com.aquakloud.ECommerce.dto.user.SignInResponseDTO;
import com.aquakloud.ECommerce.dto.user.SignupDTO;
import com.aquakloud.ECommerce.exceptions.AuthenticationFailException;
import com.aquakloud.ECommerce.exceptions.CustomException;
import com.aquakloud.ECommerce.model.AuthenticationToken;
import com.aquakloud.ECommerce.model.User;
import com.aquakloud.ECommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    public ResponseDTO signUp(SignupDTO signupDTO) {

        //check if user already present
        if(Objects.nonNull(userRepo.findByEmail(signupDTO.getEmail()))){
            throw new CustomException("user already present");
        }
        // hash the password

        String encryptedpassword = signupDTO.getPassword();
        try{
            encryptedpassword = hashPassword(signupDTO.getPassword());
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        // save the user

        User user = new User(signupDTO.getFirstName(), signupDTO.getLastName(), signupDTO.getEmail(), encryptedpassword);
        userRepo.save(user);


        // create the token

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDTO responseDTO = new ResponseDTO("success","dummy response");
        return responseDTO;

    }

    private String hashPassword(String password)  throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDTO signIn(SignInDTO signInDTO) {
        // find user by email
        User user = userRepo.findByEmail(signInDTO.getEmail());
        if(Objects.isNull(user)){
            throw new AuthenticationFailException("user is not valid");
        }

        // hash the password
        //String hashedPassword;
            try {
                if (!user.getPassword().equals(hashPassword(signInDTO.getPassword()))) {
                    throw new AuthenticationFailException("wrong password");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            // compare the password in DB

            // if password match


            AuthenticationToken token = authenticationService.getToken(user);

            // retrive the token

            if (Objects.isNull(token)) {
                throw new CustomException("token is not present");
            }

            return new SignInResponseDTO("sucess", token.getToken());


    }
}
