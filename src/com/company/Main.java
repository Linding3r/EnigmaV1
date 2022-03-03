package com.company;


import java.util.Scanner;

public class Main {
  final char[] alphabet = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};
  Scanner sc = new Scanner(System.in);


  //********************************************************
  //**********CEASAR-CIPHER ENCRYPTION/DECRYPTION***********
  //********************************************************

  //Ceasar-cipher method which returns the encrypted  text. Is also used for decrypting,
  //though there the user's shift input is given a negative value in decryptInterface
  public String ceasarEncryption(String text, int shiftValue) {
    String ceasarText = "";
    int length = textToNumberShiftArray(text, shiftValue).length;
    int[] encryptedNumbers = textToNumberShiftArray(text, shiftValue);
    for (int x = 0; x < length; x++) {
      int position = encryptedNumbers[x];
      ceasarText += "" + convertNumberToLetter(position);

    }
    return ceasarText;
  }

  //converts int to char. Int as of position in the alphabet to char letter
  public char convertNumberToLetter(int position) {
    char letter = alphabet[position];
    return letter;
  }

  //Converts a char to an int representing the index value it has in the alphabet[] and returns the position
  public int convertLetterToNumber(char letter) {
    int position = 0;
    for (int x = 0; x < alphabet.length; x++) {
      if (letter == alphabet[x]) {
        position = x;
      }
    }
    return position;
  }

  //Convert the String the user inputs to a char[] and then into an int[] which it then returns
  public int[] textToNumberShiftArray(String text, int shiftValue) {
    char[] textCharAnte = text.toCharArray();
    int[] numberArray = new int[textCharAnte.length];
    for (int x = 0; x < textCharAnte.length; x++) {
      int shiftedNumber = convertLetterToNumber(textCharAnte[x]);
      if (shiftedNumber != 0) {   //0 being " " in the alphabet array as we do not want to shift that in our encrypted code
        shiftedNumber += shiftValue;
        if (shiftedNumber > (alphabet.length - 1)) {
          shiftedNumber -= (alphabet.length - 1);
        }
      }
      numberArray[x] = shiftedNumber;
    }
    return numberArray;
  }

  //********************************************************
  //**********VIGENERE-CIPHER ENCRYPTION/DECRYPTION*********
  //********************************************************

  //Main Vigenere encryption method
  public String vigenereEncryption(String text, String key) {
    key = keyGenerator(text, key);
    String vigenereText = "";
    for (int x = 0; x < text.length(); x++) {
      if (convertLetterToNumber(text.charAt(x)) == 0) {
        vigenereText += " ";
      } else {
        int position = convertLetterToNumber(key.charAt(x)) + convertLetterToNumber(text.charAt(x));
        if (position > (alphabet.length-1)) {
          position -= (alphabet.length-1);
        }
        vigenereText += "" + convertNumberToLetter(position);
      }
    }
    return vigenereText;
  }

  //Main Vigenere decryption method
  public String vigenereDecryption(String text, String key) {
    key = keyGenerator(text, key);
    String decryptedText = "";
    int position = 0;
    for (int x = 0; x < text.length(); x++) {
      if (convertLetterToNumber(text.charAt(x)) == 0) {
        decryptedText += " ";
      } else {
        position = convertLetterToNumber(text.charAt(x)) - convertLetterToNumber(key.charAt(x));
        if (position < 0) {
          position += (alphabet.length - 1);
        }decryptedText += "" + convertNumberToLetter(position);
      }
    }
    return decryptedText;
  }

  //Generate the key by looping the key string to the length of the desired text to encrypt
  //If text = "This is a test"   and key "test", the method will return key = "testtesttestte"
  public String keyGenerator(String text, String key) {
    for (int x = 0; x < text.length(); x++) {
      key += key.charAt(x);
    }
    return key;
  }

  //********************************************************
  //*******************INTERFACES/MENU**********************
  //********************************************************

  //Encryption Interface with the various choices
  public void encryptInterface() {
    System.out.println("You have chosen to encrypt.");
    System.out.println("Please chose chose one of the following options:");
    System.out.println("\nPress 1: Encrypt in Ceasar-Cipher");
    System.out.println("Press 2: Encrypt in Vigenère-Cipher");
    System.out.println("Press 9: Main Menu");
    String input = sc.next();
    switch (input) {
      case "1":
        System.out.println("Please type the Shift value for your encryption");
        int shiftValue = sc.nextInt();
        sc.nextLine(); //Fix of Scanner Bug
        System.out.println("\nPlease type the text you wish to encrypt:");
        String text = sc.nextLine();
        System.out.println("\nHere is your Ceasar-Cipher encrypted text");
        System.out.println("\u001B[31m" + ceasarEncryption(text, shiftValue) + "\u001B[0m");
        break;
      case "2":
        System.out.println("Please type the text you wish to encrypt:");
        sc.nextLine(); //Fix of Scanner Bug
        String text2 = sc.nextLine();
        System.out.println("\nPlease type the desired Key for your encryption");
        String key = sc.nextLine();
        System.out.println("\nHere is your Vigenère-Cipher encrypted text");
        System.out.println("\u001B[31m" + vigenereEncryption(text2, key) + "\u001B[0m");
        break;
      case "9":
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  //Decryption interface with the various choices
  public void decryptInterface() {
    System.out.println("You have chosen to decrypt.");
    Boolean run = true;
    while (run) {
      System.out.println("Please chose chose one of the following options:");
      System.out.println("\nPress 1: Decrypt from Ceasar-Cipher");
      System.out.println("Press 2: Decrypt from Vigenère-Cipher");
      System.out.println("Press 9: Main Menu");
      String input = sc.next();
      switch (input) {
        case "1":
          System.out.println("Please type the Shift value of the encryption");
          int shiftValue = sc.nextInt();
          System.out.println("Please type the text you wish to decrypt:");
          String text = sc.next();
          String encryptedText = ceasarEncryption(text, -(shiftValue));
          System.out.println("\nHere is your Ceasar-Cipher decrypted text");
          System.out.println("\u001B[31m" + encryptedText + "\u001B[0m");
          break;
        case "2":
          System.out.println("Please type the text you wish to decrypt:");
          sc.nextLine(); //Fix of Scanner Bug
          String text2 = sc.nextLine();
          System.out.println("\nPlease type the Key for the decryption");
          String key = sc.nextLine();
          System.out.println("\nHere is your Vigenère-Cipher decrypted text");
          System.out.println("\u001B[31m" + vigenereDecryption(text2, key) + "\u001B[0m");
          break;
        case "9":
          run = false;
          break;
        default:
          System.out.println("Invalid input, Please try again!");
      }
    }
  }

  //Main menu which is the one that runs the entire program and which you call
  public void mainMenu() {
    System.out.println("Welcome to the Encrypter/Decrypter 3000!");
    boolean run = true;
    while (run) {
      System.out.println("\nChose one of the following options:");
      System.out.println("\nPress 1: Encrypt");
      System.out.println("Press 2: Decrypt");
      System.out.println("Press 9: Quit");
      String input = sc.next();
      switch (input) {
        case "1":
          encryptInterface();
          break;
        case "2":
          decryptInterface();
          break;
        case "9":
          System.out.println("Thank you for using Encrypter/Decrypter 3000!\nSee you next time!");
          run = false;
          break;
        default:
          System.out.println("Invalid Input, Please try again!");
      }
    }
  }

  public static void main(String[] args) {
    Main prg = new Main();
    prg.mainMenu();

  }
}
