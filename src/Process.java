public class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int priority;
    int startTime;
    int endTime;
    int remainingTime;

    public Process(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }
}
