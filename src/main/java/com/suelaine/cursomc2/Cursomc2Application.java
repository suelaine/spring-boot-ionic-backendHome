package com.suelaine.cursomc2;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suelaine.cursomc2.domain.Categoria;
import com.suelaine.cursomc2.domain.Cidade;
import com.suelaine.cursomc2.domain.Cliente;
import com.suelaine.cursomc2.domain.Endereco;
import com.suelaine.cursomc2.domain.Estado;
import com.suelaine.cursomc2.domain.ItemPedido;
import com.suelaine.cursomc2.domain.Pagamento;
import com.suelaine.cursomc2.domain.PagamentoComBoleto;
import com.suelaine.cursomc2.domain.PagamentoComCartao;
import com.suelaine.cursomc2.domain.Pedido;
import com.suelaine.cursomc2.domain.Produto;
import com.suelaine.cursomc2.domain.enums.EstadoPagamento;
import com.suelaine.cursomc2.domain.enums.TipoCliente;
import com.suelaine.cursomc2.repositories.CategoriaRepository;
import com.suelaine.cursomc2.repositories.CidadeRepository;
import com.suelaine.cursomc2.repositories.ClienteRepository;
import com.suelaine.cursomc2.repositories.EnderecoRepository;
import com.suelaine.cursomc2.repositories.EstadoRepository;
import com.suelaine.cursomc2.repositories.ItemPedidoRepository;
import com.suelaine.cursomc2.repositories.PagamentoRepository;
import com.suelaine.cursomc2.repositories.PedidoRepository;
import com.suelaine.cursomc2.repositories.ProdutoRepository;

@SpringBootApplication
public class Cursomc2Application implements CommandLineRunner {
	//permite implementar o médtoo auxiliar pra executar uma ação quando a aplicação iniciar
	
	@Autowired
	private CategoriaRepository categoriaRepository;//dependência
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired	
	private PagamentoRepository pagamentoRepository;
	@Autowired	
	private ItemPedidoRepository itemPedidoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(Cursomc2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//cria objetos
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama, mesa e banho");
		Categoria cat4 = new Categoria(null,"Eletronicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");
		
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1); 
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		
			
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36734267845",TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null,"Rua das flores","300","Apt305","Jardim","21412412",cli1,c1);
		Endereco e2 = new Endereco(null,"Av Matos","105 ","sala 800","Centro ","21412412",cli1,c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);		
		
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 19:35"),null);
		ped2.setPagamento(pagto2);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		
		//transforma EM LISTA 
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		cli1.getTelefones().addAll(Arrays.asList("412124","124124"));
		
		
		
		 cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		 
		 cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		 
		 
		  
		//salva tudo no banco de dados
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

		
	}
}

