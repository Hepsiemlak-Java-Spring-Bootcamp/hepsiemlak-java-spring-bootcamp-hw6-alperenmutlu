package emlakburada.controller;

import emlakburada.model.dto.reponse.AdvertResponse;
import emlakburada.model.dto.request.AdvertRequest;
import emlakburada.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @GetMapping("/adverts")
    public ResponseEntity<List<AdvertResponse>> getAllAdvert(){
        return new ResponseEntity<>(advertService.getAllAdvert(), HttpStatus.OK);
    }


    @PostMapping("/adverts")
    public ResponseEntity<?> saveAdvert(@RequestBody AdvertRequest advertRequest) throws Exception {
        advertService.saveAdvert(advertRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/adverts/{id}")
    public ResponseEntity<AdvertResponse> getAdvertByAdvertd(@PathVariable(required = false) int id){
        return new ResponseEntity<>(advertService.getAdvertByAdvertId(id), HttpStatus.OK);
    }


}
