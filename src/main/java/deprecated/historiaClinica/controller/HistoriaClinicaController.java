package deprecated.historiaClinica.controller;

import com.ochobits.optica.Utils.response.ResponseObject;
import com.ochobits.optica.Utils.response.ResultsPage;
import com.ochobits.optica.Utils.response.ResponseUtil;
import com.ochobits.optica.athentication.security.config.OpticaUserDetails;
import deprecated.historiaClinica.dto.BuscarHistoriaClinicaRequest;
import com.ochobits.optica.shared.dto.HistoriaClinica;
import deprecated.historiaClinica.dto.HistoriaClinicaWrapper;
import deprecated.historiaClinica.services.create.CreateHistoriaClinicaService;
import deprecated.historiaClinica.services.delete.DeleteHistoriaClinicaService;
import deprecated.historiaClinica.services.find.BuscarHistoriaClinicaService;
import deprecated.historiaClinica.services.update.UpdateHistoriaClinicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/historia_clinica")
public class HistoriaClinicaController {

    private final CreateHistoriaClinicaService createHistoriaClinicaService;
    private final UpdateHistoriaClinicaService updateHistoriaClinica;
    private final BuscarHistoriaClinicaService buscarHistoriaClinicaService;
    private final DeleteHistoriaClinicaService deleteHistoriaClinicaService;

    public HistoriaClinicaController(CreateHistoriaClinicaService createHistoriaClinicaService, UpdateHistoriaClinicaService updateHistoriaClinica, BuscarHistoriaClinicaService buscarHistoriaClinicaService, DeleteHistoriaClinicaService deleteHistoriaClinicaService) {
        this.createHistoriaClinicaService = createHistoriaClinicaService;
        this.updateHistoriaClinica = updateHistoriaClinica;
        this.buscarHistoriaClinicaService = buscarHistoriaClinicaService;
        this.deleteHistoriaClinicaService = deleteHistoriaClinicaService;
    }

//    @GetMapping
//    public ResponseEntity<Object> getAllHistoriasClinicas(
//            Authentication authentication
//    ) {
//        OpticaUserDetails userDetails = (OpticaUserDetails) authentication.getPrincipal();
//
//        Object response = buscarHistoriaClinicaService.buscarHistoriaClinicaAll();
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping(params = {"fullname"})

    /// /    @GetMapping("/by")
//    public ResponseEntity<Object> getFindHistoriasClinicasPaged(
//            @RequestParam String fullname,
//            @RequestParam(defaultValue= "0") int page,
//            @RequestParam(defaultValue= "10") int size,
//            Authentication authentication
//    ){
//        OpticaUserDetails userDetails = (OpticaUserDetails) authentication.getPrincipal();
//
//        BuscarHistoriaClinicaRequest request = new BuscarHistoriaClinicaRequest(
//                fullname
//        );
//
//        Object responses = buscarHistoriaClinicaService.buscarHistoriaClinicaPage(request.nombre(),page,size);
//        return ResponseEntity.ok(responses);
//    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllHistoriasClinicas(
            Authentication authentication
    ) {
        try {

            OpticaUserDetails userDetails = (OpticaUserDetails) authentication.getPrincipal();

//        Object response = buscarHistoriaClinicaService.buscarHistoriaClinicaAll();
            //        return ResponseEntity.ok(response);

            ResultsPage response = buscarHistoriaClinicaService.buscarHistoriaClinicaAll();
            return ResponseUtil.buildResponse(response.getContent(), HistoriaClinica.class,null, null, HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseObject<HistoriaClinica> response = new ResponseObject<>(null,null,e.getMessage());
            return  ResponseUtil.buildResponse(response,HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(params = {"fullname"})
//    @GetMapping("/by")
    public ResponseEntity<Map<String,Object>> getFindHistoriasClinicasPaged(
            @RequestParam String fullname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ) {
        try {
            OpticaUserDetails userDetails = (OpticaUserDetails) authentication.getPrincipal();

            BuscarHistoriaClinicaRequest request = new BuscarHistoriaClinicaRequest(
                    fullname
            );

//        Object responses = buscarHistoriaClinicaService.buscarHistoriaClinicaPage(request.nombre(), page, size);
            ResultsPage response = buscarHistoriaClinicaService.buscarHistoriaClinicaPage(request.nombre(), page, size);
            return ResponseUtil.buildResponse(response.getContent(),HistoriaClinica.class, response.getPageDetails()
                    , null, HttpStatus.OK);
//        return ResponseEntity.ok(responses);
        } catch (RuntimeException e) {
            ResponseObject<HistoriaClinica> response = new ResponseObject<>(null,null,e.getMessage());
            return  ResponseUtil.buildResponse(response,HttpStatus.BAD_REQUEST);
        }
    }


    //    @PostMapping("/crear")
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearHistoriaClinica(@RequestBody HistoriaClinicaWrapper request) {
        try {
            HistoriaClinica historiaClinica = request.HistoriaClinica();
            var historia = createHistoriaClinicaService.execute(historiaClinica);
            return ResponseUtil.buildResponse(historia, null, "Historia Clinica guardada con exito", HttpStatus.CREATED);

        } catch (RuntimeException e) {
            ResponseObject<HistoriaClinica> response = new ResponseObject<>(null, null, e.getMessage());
            return ResponseUtil.buildResponse(response, HttpStatus.BAD_REQUEST);
        }
    }

    //    @PatchMapping("/modificar/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modificarHistoriaClinica(@PathVariable("id") Long historiaId, @RequestBody HistoriaClinicaWrapper request) {
        try {

            HistoriaClinica historiaClinica = new HistoriaClinica(
                    historiaId,
                    request.HistoriaClinica().nombre(), request.HistoriaClinica().primerApellido(),
                    request.HistoriaClinica().segundoApellido(), request.HistoriaClinica().ciudad(),
                    request.HistoriaClinica().ocupacion(), request.HistoriaClinica().edad(),
                    request.HistoriaClinica().email(), request.HistoriaClinica().mc(), request.HistoriaClinica().hea(),
                    request.HistoriaClinica().app(), request.HistoriaClinica().apf(), request.HistoriaClinica().ojo(),
                    request.HistoriaClinica().mano(), request.HistoriaClinica().directa(), request.HistoriaClinica().inversa()
            );

            updateHistoriaClinica.execute(historiaClinica);
            return ResponseUtil.buildResponse(historiaClinica, null, "Updated Historia Clinica", HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseObject<HistoriaClinica> response = new ResponseObject<>(null, null, e.getMessage());
            return ResponseUtil.buildResponse(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Map<String, Object>> deleteHistoriaClinica(@RequestParam Long id) {
        try {
            deleteHistoriaClinicaService.execute(id);
            String message = String.format("La historia clinica con número: %s, se elimino", id);
            return ResponseUtil.buildResponse(null, null, message, HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseObject<HistoriaClinica> response = new ResponseObject<>(null, null, e.getMessage());
            return ResponseUtil.buildResponse(response, HttpStatus.BAD_REQUEST);
        }
    }

}
