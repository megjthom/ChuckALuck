/**
 * Computes the payout for Chuck-a-luck.
 *
 *
 * @author MEGAN JANE THOMPSON
 * @version MARCH 1, 2019
 */
public class ChuckALuck {

   // Constants that specify bet types
   public static final int SINGLE = 1;
   public static final int TRIPLE = 2;
   public static final int BIG = 3;
   public static final int SMALL = 4;
   public static final int FIELD = 5;

   // Constants that specify payout multipliers
   public static final double SINGLE_ONE_ODDS = 1.0;
   public static final double SINGLE_TWO_ODDS = 2.0;
   public static final double SINGLE_THREE_ODDS = 10.0;
   public static final double TRIPLE_ODDS = 30.0;
   public static final double BIG_ODDS = 1.0;
   public static final double SMALL_ODDS = 1.0;
   public static final double FIELD_ODDS = 1.0;

   /**
    * Calculate the correct Chuck-a-luck payout based on the dice roll, the bet
    * type, and the bet amount.
    * 
    * For any losing roll the payout will be a negative number equal to the
    * value of the bet. For example, on a losing $3.00 bet, the payout will be
    * -$3.00.
    * 
    * @param dice       A dice object representing the outcome of the roll
    * @param betType    The type of the bet, SINGLE, TRIPLE, BIG, etc.
    * @param number     The number of the bet, 1-6. (Note that this parameter
    *                   only matters if the bet type is SINGLE. Tt will be 
    *                   ignored for all other bet types.)
    * @param betAmount  The amount of the bet in dollars
    * @return The payout amount in dollars. This will be a negative number if it
    *         was a losing bet.
    */
   public static double calculatePayout(Dice dice, int betType, int number,
               double betAmount) {

      double payout;
      payout = 0;

      // Write a switch statement to evaluate the payout.
      // Cases that require more than a few lines of code should be handled
      // in separate methods below. Each case should set the value of the
      // payout variable, so that you will have only one return statement.
      switch (betType) {
         case SINGLE:
            payout = singleBet(dice, betAmount, number);
            break;
         case TRIPLE:
            payout = tripleBet(dice, betAmount);
            break;
         case BIG:
            payout = bigBet(dice, betAmount);
            break;
         case SMALL:
            payout = smallBet(dice, betAmount);
            break;
         case FIELD:
            payout = fieldBet(dice, betAmount);
            break;
      }
      
      return payout;
   }
   
      /**
       * Calculate the correct Chuck-a-luck payour a "single" bet.
       * 
       * @param dice       A dice object representing the outcome of the dice roll.
       * @param betAmount  The amount of the bet in dollars.
       * @param number     The number of the bet; 1-6. (Note that this param
       *                   only matters if the bet type is SINGLE. It will be 
       *                   ignored for all other bet types.)
       * @return The payout amount in dallars for a "single" bet. This will return a 
       *         negative number if it was a losing bet.
       */
      public static double singleBet(Dice dice, double betAmount, int number) {
         double payout;
         payout = 0;
         
         if (dice.countValues(number) == 1) {
            payout = betAmount * SINGLE_ONE_ODDS;
         }
         else if (dice.countValues(number) == 2) {
            payout = betAmount * SINGLE_TWO_ODDS;
         }
         else if (dice.countValues(number) == 3) {
            payout = betAmount * SINGLE_THREE_ODDS;
         }
         else {
            payout = -betAmount;
         }
         
         return payout;
      }
      
      /**
       * Calculate the correct Chuck-a-luck payour a "triple" bet.
       * 
       * @param dice       A dice object representing the outcome of the dice roll.
       * @param betAmount  The amount of the bet in dollars.
       * @return The payout amount in dallars for a "triple" bet. This will return a 
       *         negative number if it was a losing bet.
       */
      public static double tripleBet(Dice dice, double betAmount) {
         int i; 
         double payout;
         
         i = 1;
         payout = 0;
        
         for (i = 1; i <= 6; ++i) {
            if (dice.countValues(i) != 3) { 
               payout = -betAmount;
            }
         }
         for (i = 1; i <= 6; ++i) {
            if (dice.countValues(i) == 3) { 
               payout = betAmount * TRIPLE_ODDS;
            }
         }
         
         return payout;
      }
            
      /**
       * Calculate the correct Chuck-a-luck payour a "big" bet.
       * 
       * @param dice       A dice object representing the outcome of the dice roll.
       * @param betAmount  The amount of the bet in dollars.
       * @return The payout amount in dallars for a "big" bet. This will return a 
       *         negative number if it was a losing bet.
       */     
      public static double bigBet(Dice dice, double betAmount) {
         final int BIG_SUM = 11;
         int i;
         double payout; 
         payout = 0;
         
         if (dice.addValues() >= BIG_SUM) {
            for (i = 1; i <= 6; ++i) {
               if (dice.countValues(i) != 3) { 
                  payout = betAmount * BIG_ODDS;
               }
            }
            for (i = 1; i <= 6; ++i) {
               if (dice.countValues(i) == 3) { 
                  payout = -betAmount;
               }
            }
         }
         else {
            payout = -betAmount;
         }
         
         return payout;
      }
                  
      /**
       * Calculate the correct Chuck-a-luck payour a "small" bet.
       * 
       * @param dice       A dice object representing the outcome of the dice roll.
       * @param betAmount  The amount of the bet in dollars.
       * @return The payout amount in dallars for a "small" bet. This will return a 
       *         negative number if it was a losing bet.
       */            
      public static double smallBet(Dice dice, double betAmount) {
         final int BIG_SUM = 10;
         int i;
         double payout; 
         payout = 0;
         
         if (dice.addValues() <= BIG_SUM) {
            for (i = 1; i <= 6; ++i) {
               if (dice.countValues(i) != 3) { 
                  payout = betAmount * BIG_ODDS;
               }
            }
            for (i = 1; i <= 6; ++i) {
               if (dice.countValues(i) == 3) { 
                  payout = -betAmount;
               }
            }
         }
         else {
            payout = -betAmount;
         }
         
         return payout;
      }
      
      /**
       * Calculate the correct Chuck-a-luck payour a "field" bet.
       * 
       * @param dice       A dice object representing the outcome of the dice roll.
       * @param betAmount  The amount of the bet in dollars.
       * @return The payout amount in dallars for a "field" bet. This will return a 
       *         negative number if it was a losing bet.
       */
      public static double fieldBet(Dice dice, double betAmount) {
         final int BIG_SUM = 12;
         final int LITTLE_SUM = 8;
         double payout; 
         payout = 0;
         
         if (dice.addValues() < LITTLE_SUM) {
            payout = betAmount * FIELD_ODDS;
         }
         else if (dice.addValues() > BIG_SUM) {
            payout = betAmount * FIELD_ODDS;
         }
         else {
            payout = -betAmount;
         }
         
         return payout;
      }
      
}
