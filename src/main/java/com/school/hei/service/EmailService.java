package com.school.hei.service;

import com.school.hei.mail.Email;
import com.school.hei.mail.Mailer;
import jakarta.mail.internet.InternetAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {
    private Mailer mailer;

    public void sendUploadConfirmation(String to, String fileName, String presignedFile) throws Exception {
        String subject = "Upload confirmation: " + fileName;
        String htmlBody = """
                <html>
                  <body>
                    <h2>Upload Successful!</h2>
                    <p>Your file has been uploaded successfully.</p>
                    <p>You can access it here:</p>
                    <p><a href="%s" style="background-color: #ababab; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;">Download your file</a></p>
                    <p><small>This link is valid for 10 minutes.</small></p>
                    <p>Thank you for using our service!</p>
                    <p>Best regards,<br/>The Team</p>
                  </body>
                </html>
                """.formatted(fileName, presignedFile);

        var email = new Email(
                new InternetAddress(to),
                List.of(),
                List.of(),
                subject,
                htmlBody,
                List.of()
        );

        mailer.accept(email);
    }
}
