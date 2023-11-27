package nhannt.com.se160792_nguyenthanhnhan.api;

public class TraineeRepository {
    public static TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }
}
