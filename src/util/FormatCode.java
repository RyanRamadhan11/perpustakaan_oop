package util;

public class FormatCode {
    private static int novelCounter = 1;
    private static int magazineCounter = 1;

    public static String formatNovelCode(int year) {
        return year + "-A-" + String.format("%05d", novelCounter++);
    }

    public static String formatMagazineCode(int year) {
        return year + "-B-" + String.format("%05d", magazineCounter++);
    }
}
