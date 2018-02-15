package de.koegler;

public class Main {

    /**
     * Handle parameter list always as following described:
     *
     *      - none parameters :  /
     *      - one parameter   :  * single day of week to set the desired start day of week for displaying the calendar
     *                           * set day in english language
     *      - two parameters  :  * first desired month as integer
     *                           * second desired year as integer
     *      - three parameters:  * first desired month as integer
     *                           * second desired year as integer
     *                           * third desired start day of week as string in english language
     *
     * In general do not shuffle the parameters. This will cause unpredictable application behaviour.
     *
     *
     * @param args      input parameter list separated with single space bars
     */
    public static void main(String[] args) {

        int arg_count = args.length;

        if (isValidInput()) {
            switch(arg_count) {
                case 0:
                    Cal.calculateCal();
                    break;

                case 1:
                    Cal.calculateCal(args[0]);
                    break;

                case 2:
                    Cal.calculateCal(args[0], args[1]);
                    break;

                case 3:
                    Cal.calculateCal(args[0], args[1], args[2]);
                    break;

                default:
                    errorMsg();
                    break;
            }
        } else {
            errorMsg();
        }
    }

    private static boolean isValidInput() {
        // Input validation is an issue, because I only get a context sensitive string... arg,
        // which is difficult to evaluate. Just try to know, what you are going to do. ;-)
        return true;
    }

    private static void errorMsg() {
        System.out.println("ERROR: Wrong parameter list. Please restart application with correct input parameters.");
        System.out.println("INFO : Consult JavaDoc for further information.");
    }
}
