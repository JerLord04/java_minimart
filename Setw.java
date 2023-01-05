public class Setw{
    public static void right(String str, int width){
        right(str, width, ' ');
    }
    public static void left(String str, int width){
        left(str, width, ' ');
    }
    public static void right(String str, int width, char fill){
        for (int x = str.length(); x < width; ++x)
        {
            System.out.print(fill);
        }
        System.out.print(str);
    }
    public static void left(String str, int width, char fill){
        System.out.print(str);
        for (int x = str.length(); x < width; ++x)
        {
            System.out.print(fill);
        }
    }
}