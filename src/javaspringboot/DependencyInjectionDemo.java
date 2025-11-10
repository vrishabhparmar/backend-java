package javaspringboot;

/**
 * Service Interface
 */
interface MessageService{
    public void sendMessage(String msg);
}

class FaceBookService implements MessageService{

    @Override
    public void sendMessage(String msg) {
        System.out.println("Message was send via FaceBook: " + msg);
    }
}

class EmailService2 implements MessageService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via Email: " + msg);
    }
}

class SmsService2 implements MessageService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via SMS: " + msg);
    }
}

/**
 * Consumer Interface
 */
interface MessageServiceBuilder{
    public void process(String message);
    public void setMessageService(MessageService messageService);
}

class MyApplication2 implements MessageServiceBuilder{

    MessageService messageService;

    public MyApplication2(){}

    public MyApplication2(MessageService messageService)
    {
        this.messageService = messageService;
    }

    public void process(String message)
    {
        messageService.sendMessage(message);
    }

    // Setter based injection
    @Override
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}

/**
 * Injector Interface
 */
interface MessageServiceInjector{
    public MessageService createService();
}

class FacebookInjectorClass implements MessageServiceInjector{

    @Override
    public MessageService createService() {
        return new FaceBookService();
    }
}

class MyEmailServiceInjector implements MessageServiceInjector{
    @Override
    public MessageService createService() {
        return new EmailService2();
    }
}

class SmsServiceInjector implements MessageServiceInjector{
    @Override
    public MessageService createService()
    {
        return new SmsService2();
    }
}

public class DependencyInjectionDemo{

    public static void main(String[] args) {

        // Send Email
        // Injector Creates a Service which you need
        MessageServiceInjector messageServiceInjector = new MyEmailServiceInjector();

        // Injector creates the required service
        MessageService service = messageServiceInjector.createService();

        // Consumer uses the service
        MyApplication2 myApplication2 = new MyApplication2(service);
        myApplication2.process("Hello");

        // Send SMS
        MessageServiceInjector messageServiceInjector2 = new SmsServiceInjector();
        MessageService service2 = messageServiceInjector2.createService();
        MyApplication2 myApplication21 = new MyApplication2();
        myApplication21.setMessageService(service2); // Setter based injection
        myApplication21.process("Hello");

        // Send Message via Facebook
        MessageServiceInjector facebookInjectorClass = new FacebookInjectorClass();
        MessageService facebookService = facebookInjectorClass.createService();
        MyApplication2 consumer = new MyApplication2(facebookService);
        consumer.process("Hello");




    }
}


