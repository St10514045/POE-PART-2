import org.junit.Test;
import static org.junit.Assert.*;

import tumeloquickchat.Part2;

public class Testing {
      @Test
    public void shouldNotAcceptMessageOver250Characters() {
        Part2 msg = new Part2();
        msg.setMsgText("a".repeat(260));
        String actual = msg.validateMsgLength();
        String expected = "Message exceeds 250 characters by [10]; please reduce the size";
        assertEquals("I expect the message to exceed the 250 character limit", expected, actual);
    }


    @Test
    public void shouldAcceptMessageUnder250Characters() {
        Part2 msg = new Part2();
        msg.setMsgText("Hi Mike, can you join us for dinner tonight?");
        String actual = msg.validateMsgLength();
        String expected = "Message ready to send";
        assertEquals("I expect the message to be within the 250 character limit", expected, actual);
    }

  
    @Test
    public void shouldAcceptValidRecipientCellNumber() {
        Part2 msg = new Part2();
        msg.setCellNumber("+27718693002");
        String actual = msg.validateCellNumber();
        String expected = "+27718693002";
        assertEquals("I expect the recipient cell number to be correctly formatted", expected, actual);
    }

    @Test
    public void shouldNotAcceptInvalidRecipientCellNumber() {
        Part2 msg = new Part2();
        msg.setCellNumber("08575975889");
        String actual = msg.validateCellNumber();
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals("I expect the recipient cell number to be incorrectly formatted", expected, actual);
    }

    @Test
    public void shouldReturnCorrectMessageHash() {
        Part2 msg = new Part2();
        msg.setMsgId("0012345678");
        msg.setMsgNumber(0);
        msg.setMsgText("Hi Mike, can you join us for dinner tonight?");
        String actual = msg.buildMessageHash();
        String expected = "00:0:HITONIGHT";
        assertEquals("I expect the message hash to be correctly generated", expected, actual);
    }

    @Test
    public void shouldGenerateMessageIdWith10Digits() {
        String actual = Part2.generateUniqueId();
        assertEquals("I expect the message ID to be 10 digits long", 10, actual.length());
    }

    @Test
    public void shouldReturnCorrectStatusWhenSendIsSelected() {
        Part2 msg = new Part2();
        msg.setDeliveryStatus("Sent");
        String actual = msg.getDeliveryStatus();
        String expected = "Sent";
        assertEquals("I expect the message status to be Sent when send is selected", expected, actual);
    }

    @Test
    public void shouldReturnCorrectStatusWhenDisregardIsSelected() {
        Part2 msg = new Part2();
        msg.setDeliveryStatus("Disregarded");
        String actual = msg.getDeliveryStatus();
        String expected = "Disregarded";
        assertEquals("I expect the message status to be Disregarded when disregard is selected", expected, actual);
    }

    @Test
    public void shouldReturnCorrectStatusWhenStoreIsSelected() {
        Part2 msg = new Part2();
        msg.setDeliveryStatus("Stored");
        String actual = msg.getDeliveryStatus();
        String expected = "Stored";
        assertEquals("I expect the message status to be Stored when store is selected", expected, actual);
    }
}
