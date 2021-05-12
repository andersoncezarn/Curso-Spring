package com.sisspring.demoajax.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sisspring.demoajax.domain.Categoria;
import com.sisspring.demoajax.domain.Promocao;
import com.sisspring.demoajax.repository.CategoriaRepository;
import com.sisspring.demoajax.repository.PromocaoRepository;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {
	
	private static Logger log = LoggerFactory.getLogger(PromocaoController.class);
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	@Autowired
	private CategoriaRepository categoriaRepostitory;
	
	@PostMapping("/save")
	private ResponseEntity<Promocao> salvarPromocao(Promocao promocao){
		log.info("Promocao {}", promocao.toString());
		promocao.setDtCadastro(LocalDateTime.now());
		promocaoRepository.save(promocao);
		return ResponseEntity.ok().build();
	}

	@ModelAttribute("categorias")
	public List<Categoria> getCategorias(){
		return categoriaRepostitory.findAll();
	}
	
	@GetMapping("/add")
	public String abrirCadastro() {
		
		return "promo-add";
	}
}
