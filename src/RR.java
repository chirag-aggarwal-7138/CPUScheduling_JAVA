import java.util.LinkedList;
import java.util.Queue;

public class RR implements Scheduler {
    private int quantum;

    public RR(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void schedule(Process[] processes) {
        Queue<Process> queue = new LinkedList<>();
        int currentTime = 0;
        int processesLeft = processes.length;

        while (processesLeft > 0) {
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.remainingTime > 0 && !queue.contains(process)) {
                    queue.add(process);
                }
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();
                if (currentProcess.remainingTime > quantum) {
                    currentProcess.startTime = currentTime;
                    currentProcess.remainingTime -= quantum;
                    currentTime += quantum;
                    queue.add(currentProcess);
                } else {
                    currentProcess.startTime = currentTime;
                    currentTime += currentProcess.remainingTime;
                    currentProcess.remainingTime = 0;
                    currentProcess.endTime = currentTime;
                    processesLeft--;
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
