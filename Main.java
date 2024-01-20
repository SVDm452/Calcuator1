import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input: ");
        String input = scanner.nextLine();
        System.out.println(calc(input));

    }
    public static String calc(String input) throws Exception {
        int x;
        int y;
        String oper;
        String output;
        boolean findRomain;
        oper = findOperation(input);
        if (oper == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - неверный оператор");
            }
        }
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда");
            }
        }

        if (RomainNum.findRomain(operands[0]) && RomainNum.findRomain(operands[1])) {
            x = RomainNum.translateToArab(operands[0]);
            y = RomainNum.translateToArab(operands[1]);
            findRomain = true;
        }
        else if (!RomainNum.findRomain(operands[0]) && !RomainNum.findRomain(operands[1])) {
            x = Integer.parseInt(operands[0]);
            y = Integer.parseInt(operands[1]);
            findRomain = false;
        }
        else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (x > 10 || y > 10 ) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Калькулятор принимает на вход числа от 1 до 10");
            }
        }
        int arab = calculation(x, y, oper);
        if (findRomain) {
            if (arab <= 0) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("В римской системе нет отрицательных чисел");
                }
            }
            output = RomainNum.translateToRomain(arab);
        }   else {
            output = String.valueOf(arab);
        }
        return output;
    }


    static String findOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calculation(int x, int y, String oper) {
        if (oper.equals("+")) return x + y;
        else if (oper.equals("-")) return x - y;
        else if (oper.equals("*")) return x * y;
        else return x / y;

    }

}

class RomainNum {
    static  String[] romainArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XXXX",
            "XXXXI", "XXXXII", "XXXXIII", "XXXXIV", "XXXXV", "XXXXVI", "XXXXVII", "XXXXVIII", "XXXXIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "LXXXX",
            "LXXXXI", "LXXXXII", "LXXXXIII", "LXXXXIV", "LXXXXV", "LXXXXVI", "LXXXXVII", "LXXXXVIII", "LXXXXIX", "C"};

    public static boolean findRomain(String val) {
        for (int i = 0; i < romainArray.length; i++) {
            if (val.equals(romainArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int translateToArab(String romain) {
        for (int i = 0; i < romainArray.length; i++) {
            if (romain.equals(romainArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String translateToRomain(int arab) {
        return romainArray[arab];
    }

}