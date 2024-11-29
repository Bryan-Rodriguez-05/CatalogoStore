package com.example.catalogostore.data

import com.example.catalogostore.services.CategoriaService
import com.example.catalogostore.services.ClienteService
import com.example.catalogostore.services.DetallePedidoService
import com.example.catalogostore.services.DevolucionService
import com.example.catalogostore.services.EntregaService
import com.example.catalogostore.services.InventarioService
import com.example.catalogostore.services.MarcaService
import com.example.catalogostore.services.PedidoService
import com.example.catalogostore.services.PersonaService
import com.example.catalogostore.services.ProductoService
import com.example.catalogostore.services.TipoComprobanteService
import com.example.catalogostore.services.UsuarioService
import com.example.catalogostore.services.VendedorService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.JsonConfiguration

interface AppContainer {
    val categoriaRepository: CategoriaRepository
    val clienteRepository: ClienteRepository
    val detallePedidoRepository: DetallePedidoRepository
    val devolucionRepository: DevolucionRepository
    val entregaRepository: EntregaRepository
    val inventarioRepository: InventarioRepository
    val marcaRepository: MarcaRepository
    val pedidoRepository: PedidoRepository
    val personaRepository: PersonaRepository
    val productoRepository: ProductoRepository
    val tipoComprobanteRepository: TipoComprobanteRepository
    val usuarioRepository: UsuarioRepository
    val vendedorRepository: VendedorRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://dsm-g02-catalogstore-backend.onrender.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val categoriaService: CategoriaService by lazy {
        retrofit.create(CategoriaService::class.java)
    }
    private val clienteService: ClienteService by lazy {
        retrofit.create(ClienteService::class.java)
    }
    private val detallePedidoService: DetallePedidoService by lazy {
        retrofit.create(DetallePedidoService::class.java)
    }
    private val devolucionService: DevolucionService by lazy {
        retrofit.create(DevolucionService::class.java)
    }
    private val entregaService: EntregaService by lazy {
        retrofit.create(EntregaService::class.java)
    }
    private val inventarioService: InventarioService by lazy {
        retrofit.create(InventarioService::class.java)
    }
    private val marcaService: MarcaService by lazy {
        retrofit.create(MarcaService::class.java)
    }
    private val pedidoService: PedidoService by lazy {
        retrofit.create(PedidoService::class.java)
    }
    private val personaService: PersonaService by lazy {
        retrofit.create(PersonaService::class.java)
    }
    private val productoService: ProductoService by lazy {
        retrofit.create(ProductoService::class.java)
    }
    private val tipoComprobanteService: TipoComprobanteService by lazy {
        retrofit.create(TipoComprobanteService::class.java)
    }
    private val usuarioService: UsuarioService by lazy {
        retrofit.create(UsuarioService::class.java)
    }
    private val vendedorService: VendedorService by lazy {
        retrofit.create(VendedorService::class.java)
    }
    override val categoriaRepository: CategoriaRepository by lazy {
        DefaultCategoriaRepository(categoriaService)
    }
    override val clienteRepository: ClienteRepository by lazy {
        DefaultClienteRepository(clienteService)
    }
    override val detallePedidoRepository: DetallePedidoRepository by lazy {
        DefaultDetallePedidoRepository(detallePedidoService)
    }
    override val devolucionRepository: DevolucionRepository by lazy {
        DefaultDevolucionRepository(devolucionService)
    }
    override val entregaRepository: EntregaRepository by lazy {
        DefaultEntregaRepository(entregaService)
    }
    override val inventarioRepository: InventarioRepository by lazy {
        DefaultInventarioRepository(inventarioService)
    }
    override val marcaRepository: MarcaRepository by lazy {
        DefaultMarcaRepository(marcaService)
    }
    override val pedidoRepository: PedidoRepository by lazy {
        DefaultPedidoRepository(pedidoService)
    }
    override val personaRepository: PersonaRepository by lazy {
        DefaultPersonaRepository(personaService)
    }
    override val productoRepository: ProductoRepository by lazy {
        DefaultProductoRepository(productoService)
    }
    override val tipoComprobanteRepository: TipoComprobanteRepository by lazy {
        DefaultTipoComprobanteRepository(tipoComprobanteService)
    }
    override val usuarioRepository: UsuarioRepository by lazy {
        DefaultUsuarioRepository(usuarioService)
    }
    override val vendedorRepository: VendedorRepository by lazy {
        DefaultVendedorRepository(vendedorService)
    }
}