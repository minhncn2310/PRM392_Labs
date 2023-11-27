package nhannt.com.se160792_nguyenthanhnhan.api;

import nhannt.com.se160792_nguyenthanhnhan.model.Office;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OfficeService {

    String OFFICES = "office";

    @POST(OFFICES)
    Call<Office> createOffice(@Body Office office);
}
