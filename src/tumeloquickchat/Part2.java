/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tumeloquickchat;

import java.util.*;


public class Part2 {
//variables 
    private String msgHash;
    private String deliveryStatus;
    private String msgId;

    private String msgText;
    private String cellNumber;
    private int msgNumber;

    public String getMsgHash() {
        return msgHash;
    }

    public void setMsgHash(String msgHash) {
        this.msgHash = msgHash;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getMsgNumber() {
        return msgNumber;
    }

    public void setMsgNumber(int msgNumber) {
        this.msgNumber = msgNumber;
    }
    //shared field variable's
    private static String sentLog = "";
    private static int sentCount = 0;
    private static ArrayList<Part1> storedMessages = new ArrayList<>();

    private static final Set<String> generatedIds = new HashSet<>();
    private static final Random rng = new Random();
    
    //method for 10 random numbers
    public static String generateUniqueId() {
        String generatedId;
        do {
            long num = 1000000000L + (long) (rng.nextDouble() * 9000000000L);
            generatedId = String.valueOf(num);
        } while (generatedIds.contains(generatedId));
        generatedIds.add(generatedId);
        return generatedId;
    }
//method for hash
     public String buildMessageHash() {
        String idPrefix = msgId.substring(0, 2);
        String opening;
        String closing;

        if (msgText.contains(" ")) {
            msgText = msgText.trim();
            opening = msgText.substring(0, msgText.indexOf(" "));
            closing = msgText.substring(msgText.lastIndexOf(" ") + 1);
            closing = closing.replaceAll("[^a-zA-Z]", "");
        } else {
            opening = msgText;
            closing = msgText;
        }

        msgHash = (idPrefix + ":" + msgNumber + ":" + opening + closing).toUpperCase();
        return msgHash;
    }
     //method to validate id
     public boolean validateMsgId(String inputId) {
      
        if (inputId.length() > 10) return false;
        return true;
    }
     //method to validateNumber
     public String validateCellNumber() {
        Part1 part = new Part1();
        if (part.validateSouthAfricanPhone(cellNumber)) {
            return cellNumber;
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
     //method to validate length of message
         public String validateMsgLength() {
        if (msgText.length() <= 250) 
            return "Message ready to send";
        int excess = msgText.length() - 250;
        return "Message exceeds 250 characters by [" + excess + "]; please reduce the size";
    }
//method for sending messages
         public String processMessages() {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many messages would you like to send? ");
        int total = sc.nextInt();
        sc.nextLine();

        if (total == 0) return "All messages have been processed successfully, returning to the main menu";

        String outcome = "";
        for (int i = 1; i <= total; i++) {
            int option = -1;

            System.out.print("Enter recipient cell number: ");
            setCellNumber(sc.nextLine());
            while (!validateCellNumber().equals(cellNumber)) {
                System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
                System.out.print("Enter recipient cell number: ");
                setCellNumber(sc.nextLine());
            }
            
            do {
                System.out.println("Message " + i + ":");
                System.out.println("Enter your message: ");
                setMsgText(sc.nextLine());
                if (getMsgText().length() > 250) System.out.println(validateMsgLength());
            } while (getMsgText().length() > 250);

            setMsgId(generateUniqueId());
            setMsgNumber(i);
            setMsgHash(buildMessageHash());

            System.out.println("Message ID: " + getMsgId());
            System.out.println("Message Hash: " + getMsgHash());
            System.out.println("Recipient: " + getCellNumber());
            System.out.println("Message: " + getMsgText());
            while (option < 1 || option > 3) {
                System.out.println("Choose an option:");
                System.out.println("1. Send message");
                System.out.println("2. Disregard message");
                System.out.println("3. Store message to send later");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        outcome = "Message successfully sent.";
                        deliveryStatus = "Sent";
                        sentLog = sentLog + msgText + "\n";
                        sentCount++;
                        break;
                    case 2:
                        outcome = "Press 0 to delete the message.";
                        deliveryStatus = "Disregarded";
                        break;
                    case 3:
                        outcome = "Message successfully stored.";
                        deliveryStatus = "Stored";
                         break;
                    default:
                        outcome = "Invalid option";
                        deliveryStatus = "Unknown";
                        break;
                }
            }
System.out.println(outcome);
            System.out.println("Message ID: " + getMsgId());
            System.out.println("Message Hash: " + getMsgHash());
            System.out.println("Message Number: " + getMsgNumber());
            System.out.println("Recipient: " + getCellNumber());
            System.out.println("Message: " + getMsgText());
            System.out.println("Status: " + deliveryStatus);
        }
        return "Total messages sent: " + sentCount + "\nAll messages has been processed successfully, returning to the main menu";
    }
         //method to get messages
          public String fetchSentMessages() {
        if (sentLog.isEmpty()) return "No messages have been sent yet.";
        return sentLog;
    }
//method to get total messages sent
    public int getSentCount() {
        return sentCount;
    }
}
