package com.sistema.agenda.controller;

import com.sistema.agenda.model.Contato;
import com.sistema.agenda.service.ContatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contatos")

public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@GetMapping
	public String getAllContatos(Model model) {
		List<Contato> contatos = contatoService.findAll();
		model.addAttribute("contatos", contatos);
		return "contatos";
	}

	@GetMapping("/{id}")
	public String getAllContatoById(@PathVariable Long id, Model model) {
		Contato contato = contatoService.findById(id).orElse(null);
		model.addAttribute("contato", contato);
		return "contato";
	}

	@GetMapping("/novo")
	public String showCreateForm(Model model) {
		model.addAttribute("contato", new Contato());
		return "createContato";
	}

	@PostMapping
	public String createContato(@ModelAttribute Contato contato) {
		contatoService.save(contato);
		return "redirect:/contatos";
	}

	@GetMapping("/editar/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
		Contato contato = contatoService.findById(id).orElse(null);
		model.addAttribute("contato", contato);
		return "updateContato";
	}

	@PostMapping("/atualizar/{id}")
	public String updateContato(@PathVariable Long id, @ModelAttribute Contato contato) {

		contatoService.findById(id).ifPresent(existingContato -> {

			existingContato.setNome(contato.getNome());

			existingContato.setTelefone(contato.getTelefone());

			existingContato.setEmail(contato.getEmail());
			contatoService.save(existingContato);
		});
		return "redirect:/contatos";

	}

	@GetMapping("/deletar/{id}")
	public String deleteContato(@PathVariable Long id) {
		contatoService.deleteById(id);
		return "redirect:/contatos";

	}

}
