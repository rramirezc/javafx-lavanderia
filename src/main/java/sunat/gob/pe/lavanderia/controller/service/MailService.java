package sunat.gob.pe.lavanderia.controller.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
public class MailService {

  /**
   *
   * @param destinatario
   * @param asunto
   * @param cuerpo
   */
  public static boolean enviarConGMail(String destinatario, String asunto, String cuerpo) {

    //La dirección de correo de envío
    String remitente = "rj.ramirez.ca@gmail.com";
    //La clave de aplicación obtenida según se explica en este artículo:
    String claveemail = "vhuh yqfw gepg syre";

    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", claveemail);    //La clave de la cuenta
    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(remitente));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));//Se podrían añadir varios de la misma manera
      message.setSubject(asunto);
      message.setText(cuerpo);
      Transport transport = session.getTransport("smtp");
      transport.connect("smtp.gmail.com", remitente, claveemail);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      return true;
    } catch (MessagingException me) {
      me.printStackTrace();   //Si se produce un error
      return false;
    }
    /*
    final String username = "rj.ramirez.ca@gmail.com";
    final String password = "vhuh yqfw gepg syre";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    }
    );

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("no-reply@gmail.com"));
      message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse("rj.ramirez.ca@gmail.com"));
      message.setSubject("Testing Subject");
      message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");
      Transport.send(message);
      System.out.println("Done");
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
     */
  }

}
