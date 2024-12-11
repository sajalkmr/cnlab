import java.util.Scanner;

public class SlidingWindowProtocol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Window Size: ");
        int windowSize = scanner.nextInt();

        System.out.print("\nEnter number of frames to transmit: ");
        int frameCount = scanner.nextInt();

        int[] frames = new int[frameCount];
        System.out.println("\nEnter the " + frameCount + " frames:");
        for (int i = 0; i < frameCount; i++) {
            frames[i] = scanner.nextInt();
        }

        for (int i = 0; i < frameCount; i++) {
            System.out.println("Frame " + frames[i] + " sent.");
            if ((i + 1) % windowSize == 0) {
                System.out.println("Acknowledgment of above frames received by sender\n");
            }
        }

        if (frameCount % windowSize != 0) {
            System.out.println("Acknowledgment of above frames received by sender\n");
        }

        scanner.close();
    }
}
