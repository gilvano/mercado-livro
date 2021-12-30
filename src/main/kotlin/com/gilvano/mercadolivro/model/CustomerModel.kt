package com.gilvano.mercadolivro.model

import com.gilvano.mercadolivro.enums.CustomerStatus
import com.gilvano.mercadolivro.enums.Role
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    var status: CustomerStatus,

    @Column
    var password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    var roles: Set<Role>

)
