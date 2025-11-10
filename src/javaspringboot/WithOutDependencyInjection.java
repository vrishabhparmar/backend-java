package javaspringboot;
/*
* Scenario:
* Create a Message service Api
* */

/**
 * Concrete Class which gives email service
 */
class EmailService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via Email: " + msg);
    }
}

/**
 * Concrete class which gives sms service
 */
class SmsService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via SMS: " + msg);
    }
}

/**
 * If you want to add a new Service we need to create a new concrete class like for facebook
 */
//*** New Class To Add a new Message Service ***//

/*
  This is the consumer class "MyApplication" which is responsible to use services
* */
class MyApplication {

    // Initialize each services if you want to use that service
    // Whenever a new service is added it rejects the Open for extension and close for modification solid principle
    EmailService emailService = new EmailService();
    SmsService smsService = new SmsService();
    // Add new Service like Facebook message

    public void process(String message, String msgService)
    {
        if(msgService.equals("SMS")) smsService.sendMessage(message);
        else if (msgService.equals("EMAIL")) {
            emailService.sendMessage(message);
        }
        // Add new Service like Facebook message
    }
}


class WithOutDependencyInjection{


    public static void main(String[] args) {

        MyApplication application = new MyApplication();

        application.process("Hello from Normal Classs", "EMAIL");
        application.process("Hello from Normal Classs", "SMS");

    }
}





