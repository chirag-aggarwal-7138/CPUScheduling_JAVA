import java.util.Arrays;

public class SJF implements Scheduler {
    @Override
    public void schedule(Process[] processes) {
        Arrays.sort(processes, (p1, p2) -> {
            if (p1.arrivalTime != p2.arrivalTime) {
                return p1.arrivalTime - p2.arrivalTime;
            } else {
                return p1.burstTime - p2.burstTime;
            }
        });

        int currentTime = 0;
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }
            process.startTime = currentTime;
            process.endTime = currentTime + process.burstTime;
            currentTime = process.endTime;
        }

        printSchedule(processes);
    }

    private void printSchedule(Process[] processes) {
        System.out.println("Process ID\tArrival Time\tBurst Time\tStart Time\tEnd Time");
        for (Process process : processes) {
            System.out.println(process.id + "\t\t" + process.arrivalTime + "\t\t" + process.burstTime + "\t\t" + process.startTime + "\t\t" + process.endTime);
        }
    }
}
