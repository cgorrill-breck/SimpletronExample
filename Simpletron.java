public class Simpletron{
    private int[] memory = new int[Utilities.MAX_INSTRUCTIONS];
    private int accumulator;

    public void processInstruction(int instruction){
        int opCode = instruction / 100;
        int operand = instruction % 100;
        executeInstruction(operand, opCode);
    }

    private void executeInstruction(int operand, int opCode){
        switch(opCode){
            case OPCodes.READ:
            break;
            case OPCodes.WRITE:
            break;
            case OPCodes.LOAD:
            break;
            case OPCodes.STORE:
            break;
            case OPCodes.ADD:
            break;
            case OPCodes.SUBTRACT:
            break;
            case OPCodes.MULTIPLY:
            break;
            case OPCodes.DIVIDE:
            break;
            case OPCodes.BRANCH:
            break;
            case OPCodes.BRANCHNEG:
            break;
            case OPCodes.BRANCHZERO:
            break;
            case OPCodes.HALT:
            break;

        }
    }


    public int[] getMemory(){
        return memory;
    }
}