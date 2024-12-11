import java.util.Scanner;

class LeakyBucketQueue {
    int[] queue;
    int front = 0, rear = 0;
    final int maxSize = 5;

    public LeakyBucketQueue() {
        queue = new int[maxSize];
    }

    void insertPackets(int packetCount) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < packetCount; i++) {
            System.out.print("Enter packet " + (i + 1) + ": ");
            int packet = scanner.nextInt();
            if (rear >= maxSize) {
                System.out.println("Queue is full! Packet " + packet + " is lost.");
                break;
            } else {
                queue[rear] = packet;
                rear++;
            }
        }
    }

    void leakPackets() {
        if (rear == 0) {
            System.out.println("Queue is empty!");
        } else {
            System.out.println("Leaking packets...");
            for (int i = front; i < rear; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error in thread sleep");
                }
                System.out.println("Leaked Packet: " + queue[i]);
            }
            rear = 0;
        }
    }

    public static void main(String[] args) {
        LeakyBucketQueue lbQueue = new LeakyBucketQueue();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of packets to send: ");
        int packetCount = scanner.nextInt();
        lbQueue.insertPackets(packetCount);
        lbQueue.leakPackets();
    }
}
