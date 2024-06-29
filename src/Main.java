import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int numProcesses = scanner.nextInt();
        Process[] processes = new Process[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter arrival time, burst time, and priority for process " + (i + 1) + ":");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            int priority = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime, priority);
        }

        System.out.println("Choose the scheduling algorithm:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. Round Robin");
        System.out.println("4. Priority Scheduling");
        System.out.println("5. SRTF");

        int choice = scanner.nextInt();
        Scheduler scheduler = null;

        switch (choice) {
            case 1:
                scheduler = new FCFS();
                break;
            case 2:
                scheduler = new SJF();
                break;
            case 3:
                System.out.println("Enter time quantum:");
                int quantum = scanner.nextInt();
                scheduler = new RR(quantum);
                break;
            case 4:
                scheduler = new PriorityScheduling();
                break;
            case 5:
                scheduler = new SRTF();
                break;
            default:
                System.out.println("Invalid choice");
                System.exit(0);
        }

        scheduler.schedule(processes);
    }
}
