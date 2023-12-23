import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Demo {
    public static void main(String[] args) {
        LocalDate newDate = dateInput("Enter a date (like 3/3/17): ");
    }
    public static LocalDate dateInput(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        System.out.println(date);
        return date ;
    }
}
