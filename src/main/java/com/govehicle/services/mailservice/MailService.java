package com.govehicle.services.mailservice;

import com.govehicle.controller.mailcontrollers.InvalidMailException;
import com.govehicle.entities.mail.MailStructure;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.RandomAccess;
import java.util.random.RandomGenerator;

@Service
public class MailService {

    // variable which stores otp
    private String otp;


    // method which generates otp
    private void generateOtp(int length){

        String numbers = "0123456789";

        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for(int i = 0; i< length; i++){
            int index = random.nextInt(numbers.length());
            otp.append(numbers.charAt(index));
        }
        System.out.println("Generated OTP: "+ otp.toString());
        this.otp = otp.toString();
    }


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;


    public void sendMail(String mail) throws InvalidMailException {
        if(!isValidEmail(mail)){
            throw new InvalidMailException("invalid mail address");
        }
        try{
            generateOtp(4);

            MailStructure mailStructure = new MailStructure();
            mailStructure.setSubject("Your One-Time Password (OTP) for GoVehicles");
            mailStructure.setMessage(
                    "Thank you for using GoVehicles! To ensure the security of your account, we have generated a One-Time Password (OTP) for you.\n" +
                            "\n" +
                            "Your OTP is: "+otp.toString()+ "\n" +
                            "\n" +
                            "Please use this OTP to complete your action or login to your account. Note that this OTP is valid for a short period of time.\n" +
                            "\n" +
                            "If you did not request this OTP or have any concerns about the security of your account, please contact our support team immediately.\n" +
                            "\n" +
                            "Thank you,\n" +
                            "The GoVehicles Team\n");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromMail);
            simpleMailMessage.setSubject(mailStructure.getSubject());
            simpleMailMessage.setText(mailStructure.getMessage());
            simpleMailMessage.setTo(mail);

            javaMailSender.send(simpleMailMessage);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    private boolean isValidEmail(String email) {

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        }catch (AddressException e){
            return false;
        }
    }


    public int verifyOtp(String otp){

        System.out.println(this.otp.equals(otp));

        if(this.otp.equals(otp))
            return 0;
        else
            return 1;
    }

}
