public class SimpletronExtended{
    private int[] memory = new int[Utilities.MAX_INSTRUCTIONS_EXTENDED];
    private int accumulator;
    private int instructionCounter = 0;
    private int numInstructions;
    private int opCode;
    private int operand;
    private int register;

    public SimpletronExtended(){
        Utilities.printStartScreenExtended();
        memory = Utilities.loadInstructionsExtended();
        setNumInstructions();
        Utilities.printEndInput();
        executeProgram();
        printMemoryDump();
    }

    public void executeProgram(){
        int instruction = memory[instructionCounter];
        while(instruction != Utilities.END_OF_INSTRUCTIONS_EXTENDED){
            if (isValidInstruction(instruction)){
                processInstruction(instruction);
            } else {
                System.out.println("*** Attempt to execute invalid instruction\t***");
                System.out.println("*** Simpletron execution abnormally terminated\t***");
                instructionCounter = numInstructions;
            }
            instruction  = memory[instructionCounter];
        }    
    }

    public void processInstruction(int instruction){
        opCode = instruction / 1000;
        operand = instruction % 1000;
        register = instruction;
        executeInstruction(operand, opCode);
    }

    private boolean isValidInstruction(int instruction){
        return instruction > -100000 && instruction < 100000 || instruction == Utilities.END_OF_INSTRUCTIONS_EXTENDED;
    }

    private void executeInstruction(int operand, int opCode){
        switch(opCode){
            case OPCodes.READ:
                memory[operand] = Utilities.readData();
                break;
            case OPCodes.WRITE:
                System.out.println(memory[operand]);
                break;
            case OPCodes.LOAD:
                accumulator = memory[operand];
                break;
            case OPCodes.STORE:
                memory[operand] = accumulator;
                break;
            case OPCodes.ADD:
                accumulator += memory[operand];
                break;
            case OPCodes.SUBTRACT:
                accumulator -= memory[operand];
                break;
            case OPCodes.MULTIPLY:
                accumulator *= memory[operand];
                break;
            case OPCodes.DIVIDE:
                try{
                    accumulator /= memory[operand];
                } catch (ArithmeticException e){
                    System.out.println("*** Attempt to divide by zero\t\t\t\t***");
                    System.out.println("*** Simpletron execution abnormally terminated\t***");
                    instructionCounter = numInstructions;
                }
                    
                break;
            case OPCodes.BRANCH:
                instructionCounter = operand;
                break;
            case OPCodes.BRANCHNEG:
                if(accumulator < 0){
                    instructionCounter = operand;
                } else {
                    instructionCounter++;
                }
                break;
            case OPCodes.BRANCHZERO:
                if(accumulator == 0){
                    instructionCounter = operand;
                } else {
                    instructionCounter++;
                }
                break;
            case OPCodes.HALT:
                System.out.println("*** Simpletron execution terminated ***");
                instructionCounter = numInstructions;
                break;
            default:
                System.out.println("*** Invalid Instruction\t\t\t\t***");
                System.out.println("*** Simpletron execution abnormally terminated\t***");
                instructionCounter = numInstructions;
            }//end switch
        if(opCode < OPCodes.BRANCH){
            instructionCounter++;
        }
    }

    public void setNumInstructions(){
        int i = 0;
        while(memory[i] != Utilities.END_OF_INSTRUCTIONS_EXTENDED){
            i++;
        }
        numInstructions =  i;
    }
    
    private void printMemoryDump(){
        printRegisters();
        System.out.println("MEMORY:");
        for(int i = 0; i < 10; i++){
            System.out.print("\t" + i);
        }
        for(int i = 0; i < memory.length / 10; i++){
            System.out.println();
            System.out.print(i + "\t");
            for(int j = 0; j < 10; j++){
                if(memory[i * 10 + j] >= 0){
                    System.out.print("+");
                }
                if(memory[i * 10 + j] == 0){
                    System.out.print("0000\t");
                } else {
                    System.out.print(memory[i * 10 + j] + "\t");
                }
            }
        }
        System.out.println();
    }

    private void printRegisters(){
        System.out.println();
        System.out.println("REGISTERS:");
        System.out.println("accumulator:\t\t" + accumulator);
        System.out.println("instructionCounter:\t" + instructionCounter);
        System.out.println("instructionRegister:\t" + register);
        System.out.println("operationCode:\t\t" + opCode);
        String opString = (operand == 0) ? "00" : (operand + "");
        System.out.println("operand:\t\t" + opString);
        System.out.println();
    }

    public int[] getMemory(){
        return memory;
    }
}