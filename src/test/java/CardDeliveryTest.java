import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    LocalDate localDate = LocalDate.now().plusDays(3);
    DateTimeFormatter data = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String strData = localDate.format(data);

    @Test
    public void shouldCardTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").val("Майкоп");
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(strData);
        $x("//input[@name=\"name\"]").val("Иван Петров-Орлов");
        $x("//*[@data-test-id=\"phone\"]/span/span/input").val("+79094709999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(withText("Забронировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + (strData)), Duration.ofSeconds(15));

    }


}


