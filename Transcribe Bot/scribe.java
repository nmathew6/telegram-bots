import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Scribe extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {
        long unixSecs = update.getMessage().getDate();
        // convert seconds to milliseconds
        Date date = new java.util.Date(unixSecs*1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("h:mm a");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-7"));
        String formattedDate = sdf.format(date);
        //System.out.println(formattedDate);

        String output = formattedDate + ": " + update.getMessage().getText() + " - " + update.getMessage().getFrom().getFirstName() + "\n";


        try {
            FileWriter myWriter = new FileWriter("filename.txt", true);
            myWriter.write(output);
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public String getBotUsername() {
        return "scribe_bot";
    }

    public String getBotToken() {
        return "Bot token here"; // From bot father
    }
}
