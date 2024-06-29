import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SRTF implements Scheduler {
    @Override
    public void schedule(Process[] processes) {
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.remainingTime));
        int currentTime = 0;
        int completed = 0;
        int n = processes.length;

        while (completed != n) {
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.remainingTime > 0) {
                    queue.add(process);
                }
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();
                currentProcess.startTime = currentTime;
                currentTime++;
                currentProcess.remainingTime--;

                if (currentProcess.remainingTime == 0) {
                    currentProcess.endTime = currentTime;
                    completed++;
                } else {
                    queue.add(currentProcess);
                }
            } else {
                currentTime++;
            }
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
