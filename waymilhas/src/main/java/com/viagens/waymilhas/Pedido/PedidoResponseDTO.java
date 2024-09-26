@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private List<ItemPedido> itens;
    private Double total;

    // Campos do cliente que você deseja retornar
    private Long clienteId;
    private String clienteNome;
    private String clienteEmail;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.itens = pedido.getItens();
        this.total = pedido.getTotal();
        
        // Preenchendo informações do cliente
        if (pedido.getCliente() != null) {
            this.clienteId = pedido.getCliente().getId();
            this.clienteNome = pedido.getCliente().getNome();
            this.clienteEmail = pedido.getCliente().getEmail();
        }
    }
}
