package delivery.stork.service;public interface MailSenderService {
    public void sendMessage(String toEmail, String subject, String text);
}
