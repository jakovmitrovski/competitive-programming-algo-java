package easiest100;

public class Deuce {
    public int getWinner(String cards, int numPlayers) {

        for (int i=0; i<cards.length(); i+=2*numPlayers){
            if (i+2*numPlayers > cards.length()) return -1;
            for (int j=0; j<2*numPlayers; j+=2){
                //System.out.println(cards.substring(i+j, i+j+2));
                if (cards.substring(i+j, i+j+2).equals("2C")) return j/2;
            }
        }

        return 0;
    }

   public static void main(String[] args) {
       Deuce d = new Deuce();
       System.out.println(d.getWinner("3H8D9DJC8C4DKS3C8H6S2H2CQD7HKD1C6H9S3S2SAHJDQSKH6D4S5C7C2DAC7S3DJH4H1H7D9H6C9C8S4CJSQC5D5S5HKCAS1DAD1SQH",
               7));
   }

}