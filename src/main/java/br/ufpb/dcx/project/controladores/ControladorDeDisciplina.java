package br.ufpb.dcx.project.controladores;

import br.ufpb.dcx.project.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ControladorDeDisciplina {

    @Autowired
    private DisciplinaServico servicoDisciplinas;

    @GetMapping("/api/disciplinas")
    public ResponseEntity<List<DTONomeIDDisciplina>> getDisciplinas( ){
        return ResponseEntity.ok(servicoDisciplinas.getDisciplinas());
    }

    @GetMapping("/api/disciplinas/{id}")
    public ResponseEntity<DTODisciplinas> getDisciplinaPorId(@PathVariable Long id){
        return ResponseEntity.ok(servicoDisciplinas.getDisciplinaPorid(id));
    }

    @PatchMapping("/auth/api/disciplinas/{id}/nota")
    public  ResponseEntity<DTODisciplinas> adicionaNota(@RequestBody DTODisciplinaNota nota, @PathVariable Long id, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoDisciplinas.adicionaNota(nota.getNota(), id, header));
    }

    @PatchMapping("/auth/api/disciplinas/{id}/likes")
    public ResponseEntity<DTODisciplinas> adicionaLikes(@PathVariable Long id, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoDisciplinas.adicionaLike(id, header));
    }

    @GetMapping("/api/disciplinas/ranking/notas")
    public ResponseEntity<List<DTODisciplinas>> getRakingDisciplinasNotas(){
        return ResponseEntity.ok(servicoDisciplinas.getRakingDisciplinasNotas());
    }

    @GetMapping("/api/disciplinas/ranking/likes")
    public ResponseEntity<List<DTODisciplinas>> getRakingDisciplinasLikes(){
        return ResponseEntity.ok(servicoDisciplinas.getRakingDisciplinasLikes());
    }

    @PostMapping("/auth/api/disciplinas/{id}/comentarios")
    public ResponseEntity<DTODisciplinas> adicionaComentario(@PathVariable Long id, @RequestBody DTOTextoComentario commentText, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoDisciplinas.adicionaComentario(id, commentText, header));
    }

    @DeleteMapping("/auth/api/disciplinas/{id}/{idComment}/comentarios")
    public ResponseEntity<DTOComentario> removeComentario(@PathVariable Long id, @PathVariable("idComment") Long idComment, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoDisciplinas.removeComentario(id, idComment, header));
    }

    @GetMapping("/api/disciplinas/{id}/comentarios")
    public ResponseEntity<List<DTOComentario>> getComentario(@PathVariable Long id,
                                                             @RequestParam(name = "filtro", required = false) String filtro){
        return ResponseEntity.ok(servicoDisciplinas.getComentario(id, filtro));
    }

    @PostMapping("/auth/api/disciplinas/{id}/tags")
    public ResponseEntity<DTOTagIdDisciplinaNome> adicionaTag(@PathVariable Long id, @RequestBody DTOTagDisciplina tag, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoDisciplinas.adicionaTag(id, tag.getTag(), header));
    }

    @GetMapping("/api/disciplinas/{id}/tags")
    public ResponseEntity<List<DTOTagDisciplina>> getTags(@PathVariable Long id){
        return ResponseEntity.ok(servicoDisciplinas.getTags(id));
    }

    @GetMapping("/api/disciplinas/tags")
    public ResponseEntity<List<DTODisciplinas>> getDisciplinasTag(@RequestParam(name = "tag", required = false) String tag){
        return ResponseEntity.ok(servicoDisciplinas.getDisciplinasTag(tag));
    }

}
