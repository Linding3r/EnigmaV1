package com.company;


import java.util.Scanner;

public class Main {
  final char[] alphabet = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};
  Scanner sc = new Scanner(System.in);

  public char convertNumberToLetter(int position) {
    char letter = alphabet[position];
    return letter;
  }

  public String cipherEncryption(String text, int shiftValue) {
    String encryptedText = "";
    int length = textToNumberShiftArray(text, shiftValue).length;
    int[] encryptedNumbers = textToNumberShiftArray(text, shiftValue);
    for (int x = 0; x < length; x++) {
      int position = encryptedNumbers[x];
      encryptedText += "" + convertNumberToLetter(position);

    }
    return encryptedText;
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
        if (shiftedNumber > (alphabet.length - 1)) {//
          shiftedNumber -= (alphabet.length - 1);
        }
      }
      numberArray[x] = shiftedNumber;
    }
    return numberArray;
  }

  //Interfaces
  public void encryptInterface() {
    System.out.println("You have chosen to encrypt.");
    System.out.println("Please chose chose one of the following options:");
    System.out.println("\nPress 1: Encrypt in Ceasar-Cipher");
    System.out.println("Press 2: Encrypt in Vigenère-Cipher");
    System.out.println("Press 3: Encrypt in Number-Cipher");
    System.out.println("Press 4: Encrypt in Substitution-Cipher");
    System.out.println("Press 9: Main Menu");
    String input = sc.next();
    switch (input) {
      case "1":
        System.out.println("Please type the Shift value for your encryption");
        int shiftValue = sc.nextInt();
        System.out.println("Please type in the text you wish to encrypt:");
        String text = sc.next();
        String encryptedText = cipherEncryption(text, shiftValue);
        System.out.println("\nHere is your Cipher encrypted text");
        System.out.println("\u001B[31m" + encryptedText + "\u001B[0m");
        break;
      case "2":
        System.out.println("Please type the text you wish to encrypt");
        break;
      case "3":
        System.out.println("Please type the text you wish to encrypt");
        break;
      case "4":
        System.out.println("Please type the text you wish to encrypt");
        break;
      case "9":
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  public void decryptInterface() {
    System.out.println("You have chosen to decrypt.");
    Boolean run = true;
    while (run) {
      System.out.println("Please chose chose one of the following options:");
      System.out.println("\nPress 1: Decrypt from Ceasar-Cipher");
      System.out.println("Press 2: Decrypt from Vigenère-Cipher");
      System.out.println("Press 3: Decrypt from Number-Cipher");
      System.out.println("Press 4: Decrypt from Substitution-Cipher");
      System.out.println("Press 9: Main Menu");
      String input = sc.next();
      switch (input) {
        case "1":
          System.out.println("ceasar");
          break;
        case "2":
          System.out.println("vigenère");
          break;
        case "3":
          System.out.println("number");
          break;
        case "4":
          System.out.println("substitution");
          break;
        case "9":
          run = false;
          break;
        default:
          System.out.println("Invalid input, Please try again!");
      }
    }
  }

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
