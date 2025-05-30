import java.util.InputMismatchException;
import java.util.Scanner;
public class Utilities {
    public static final int MAX_INSTRUCTIONS = 100;

    public static void printStartScreen(){
        System.out.println();
        System.out.println("*** Welcome to Simpletron!\t\t\t\t***");
        System.out.println("*** Please enter your program one instruction\t\t***");
        System.out.println("*** (or data word) at a time. I will display\t\t***");
        System.out.println("*** the location number and a question mark (?).\t***");
        System.out.println("*** You then type the word for that location.\t\t***");
        System.out.println("*** Type -99999 to stop entering your program.\t\t***");
        System.out.println();
    }

    public static void printMemory(int[] memory){
        int i = 0;
        while(memory[i] != 0){
            String sign = memory[i] == -99999 ? "" : "+";
            if(i < 10){
                System.out.printf("0%d ? %s%d%n", i, sign, memory[i]);
            } else {
                System.out.printf("%d ? %s%d%n", i, sign, memory[i]);
            }
            i++;
        }
    }

    public static void printEndInput(){
        System.out.println("*** Program loading completed	***");
        System.out.println("*** Program execution begins\t***");
    }

    public static int[] loadInstructions(){
        int[] instructions = new int[MAX_INSTRUCTIONS];
        Scanner s = new Scanner(System.in);
        int opCode;
        int instructionCount = 0;
        do {
            String instructionCountString = instructionCount < 10 ? "0" + instructionCount : instructionCount + "";
            System.out.print(instructionCountString + " ? ");
            opCode = s.nextInt();
            instructions[instructionCount] = opCode;
            instructionCount++;
        } while(opCode != -99999);
        return instructions;
    }

    
    public static int readData() {
        Scanner s = new Scanner(System.in);
        int val = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter an integer: ");
            try {
                val = s.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                s.next();
            }
        }
        return val;
    }
}
