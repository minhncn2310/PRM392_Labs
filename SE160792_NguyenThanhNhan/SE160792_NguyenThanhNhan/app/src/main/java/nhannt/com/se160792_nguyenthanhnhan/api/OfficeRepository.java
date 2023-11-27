package nhannt.com.se160792_nguyenthanhnhan.api;

public class OfficeRepository {
    public static OfficeService getOfficeService(){
        return APIClient.getClient().create(OfficeService.class);
    }
}
