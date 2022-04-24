import java.util.Scanner;
public class Driver {
    public static void main(String[] args) {
        
        MorseTree mt = new MorseTree();
        
        System.out.println("               **Welcome to Morse Code Coverter**");
        System.out.println("Here's a list of what you can do:");
        
        while(true){
            System.out.println("1. Convert letter to Morse Code");
            System.out.println("2. Translate Morse Code to a letter");
            System.out.println("3. Exit\n");
            
            System.out.println("Choose your option: ");
            Scanner sc = new Scanner (System.in);
            int choice = sc.nextInt();
            
            while (!validateChoice(choice)){
                System.out.println("Enter a valid choice: ");
                choice = sc.nextInt();
            }
            
            switch (choice){
                case 1: {
                    System.out.println("Enter a Letter to convert (CAPS): ");
                    char c = sc.next().charAt(0);
                    System.out.println("Morse: "+ mt.toMorseCode(c));
                    break;
                }
                
                case 2: {
                    System.out.println("Enter a Morse string to translate to letter: ");
                    String morse = sc.next();
                    System.out.println("Letter: "+ mt.translateString(morse));
                    break;
                }
                
                case 3: {
                    System.exit(0);
                    break;
                }
                
                default: {
                    System.out.println("invalid input");
                    break;
                }
                
            }  
            System.out.println();         
        }       
    }
    
    public static boolean validateChoice(int choice){
        
        for(int i=1;i<4;i++){
            if(choice == i) return true;
        }
        return false;
    }
}
