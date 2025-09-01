package com.example.fooddeliveryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.entity.Vendor
import com.example.fooddeliveryapp.data.repository.VendorRepository
import com.example.fooddeliveryapp.viewmodel.state.VendorUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class VendorViewModel(private val repository: VendorRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(VendorUiState())
    val uiState: StateFlow<VendorUiState> = _uiState.asStateFlow()

    fun loadVendor(id: Long) {
        viewModelScope.launch {
            repository.getVendorById(id).collect { vendor ->
                _uiState.value = VendorUiState(vendor = vendor, isLoading = false)
            }
        }
    }
    class VendorViewModel(private val repository: VendorRepository) : ViewModel() {
        fun updateVendor(vendor: Vendor) {
            viewModelScope.launch {
                repository.updateVendor(vendor)
            }
        }
    }


    fun toggleStatus(vendor: Vendor) {
        viewModelScope.launch {
            repository.updateVendor(vendor.copy(isOnline = !vendor.isOnline))
        }
    }
}