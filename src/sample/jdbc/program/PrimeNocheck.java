package sample.jdbc.program;

public class PrimeNocheck {
	
		    public static void main(String[] args) 
	    {
	        int number;
	        number=12;
	        
	        System.out.println(Math.sqrt(number));
	        
	        for(int i=2;i<Math.sqrt(number);i++)
	        {
	                if(number%2==0)
	                    System.out.println("Number is NOT Prime");
	                else
	                    System.out.println("Number is Prime");
	                    break;
	        }
	    }
	}

