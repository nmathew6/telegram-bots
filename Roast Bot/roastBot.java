import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;
import java.util.Random;


import java.util.ArrayList;

public class RoastBot extends TelegramLongPollingBot {


    private boolean repliesOn = false;


    public void onUpdateReceived(Update update)
    {
        System.out.print(update.getMessage().getText() + " - ");
        System.out.println(update.getMessage().getFrom().getFirstName());
        //System.out.println("message recieved");


        ArrayList<String> roasts = new ArrayList<String>();
        // Add roasts to roasts ArrayList
        roasts.add("Insert roast here");

        ArrayList<String> quotes = new ArrayList<String>();
        // Add quotes to quotes ArrayList
        quotes.add('"'+"Insert quote"+'"');
        
        ArrayList<String> pics = new ArrayList<String>();
        // Add picture file_id here (will be a combination of lowercase/uppercase letters, numbers, dashes, underscores)
        // Uncomment block of code to generate file_id for an image
        pics.add("Insert file_id");

        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();

        /* Start pic id getter
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            // Message contains photo
            // Set variables
            long chat_id = update.getMessage().getChatId();

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id

            String This_id = update.getMessage().getPhoto().get(0).getFileId();

            // Set photo caption
            String caption = "file_id: " + This_id;
            SendPhoto msg = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(This_id)
                    .setCaption(caption);
            try {
                execute(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        End pic id getter */


        System.out.println(update.getMessage().getText());
        if (update.getMessage().getText().equals("/pic@roast_bot") || update.getMessage().getText().equals("/pic"))
        {
            Random rand = new Random();

            int num = rand.nextInt(pics.size());

            SendPhoto msg = new SendPhoto()
                    .setChatId(update.getMessage().getChatId())
                    .setPhoto(pics.get(num))
                    .setCaption("This you?");
            try {
                //sendPhoto(msg); // Call method to send the photo with caption
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

        if (command.equals("/quote@roast_bot") || command.equals("/quote"))
        {
        //send random roast from array
        Random rand = new Random();

        int num = rand.nextInt(quotes.size());

        //nextInt(upperbound) generates random numbers in the range 0 to upperbound-1.
        message.setText(quotes.get(num));
        }


        else if (command.equals("/replies_off@roast_bot"))
            repliesOn = false;


        else if (command.equals("/roast@roast_bot") || command.equals("/roast"))
        {
            //send random roast from array
            Random rand = new Random();

            int num = rand.nextInt(roasts.size());

            //nextInt(upperbound) generates random numbers in the range 0 to upperbound-1.
            message.setText(roasts.get(num));
        }

        // Send preset response after each message by a particular user
        // Toggle with replies_on/off command
        
        else if ((update.getMessage().getFrom().getFirstName().equals("Add username here") && repliesOn))
        {
            message.setText("stfu");
        }

        else if (command.equals("good bot") || command.equals("Good bot"))
            message.setText("Thank you.");


        else if (command.equals("/replies_on@roast_bot"))
            repliesOn = true;

        message.setChatId(update.getMessage().getChatId());

        try
        {
            execute(message);
        } catch(TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    public String getBotUsername()
    {
        return "roast_bot";
    }

    public String getBotToken()
    {
        return "Insert bot token here"; // Generate bot token with botfather
    }
}
