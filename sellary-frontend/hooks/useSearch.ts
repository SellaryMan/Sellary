'use client'
import { useState, useCallback, useEffect } from "react"

interface ShippedProductExp {
  id: number;
  expDate: string | null;
  quantity: number;
  manufactureDate: string | null;
  lowStockThresholdDay: number | null;
  noShippingThresholdDay: number | null;
}

interface ShippedProductCost {
  id: number;
  unitPurchasePrice: number;
  boxPurchasePrice: number;
  palletPurchasePrice: number;
  unitSellingPrice: number;
  boxSellingPrice: number;
  palletSellingPrice: number;
}

interface Product {
  id: number;
  name: string;
  type: string;
  code: string;
  barcode: string | null;
  tags: string[];
  shippedProductExp: ShippedProductExp[];
  shippedProductCost: ShippedProductCost | null;
}

interface SearchElements {
  product_name?: string;
  type?: string | null;
  product_code?: string | null;
  search_type?: 'id' | 'name' | 'type' | 'code' | 'multi_condition' | null;
}

const initialSearchState: SearchElements = {
  product_name: "",
  type: null,
  product_code: null,
  search_type: null
};

interface IUseSearch {
  searchElements: SearchElements;
  setProductName: (productName: string) => void;
  setType: (type: string | null) => void;
  setProductCode: (product_code: string | null) => void;
  resetSearchElement: () => void;
  updateSearchElement: (element: Partial<SearchElements>) => void;
  products: Product[];
  filteredProducts: Product[];
  performSearch: () => void;
}

const useSearch = (): IUseSearch => {
  const [searchElements, setSearchElements] = useState<SearchElements>(initialSearchState);
  const [products, setProducts] = useState<Product[]>([]);
  const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);
  
  useEffect(() => {
    const sampleData: Product[] = [
        // data fetch
    ];
    
    setProducts(sampleData);
    setFilteredProducts(sampleData);
  }, []);

  const setProductName = useCallback((productName: string) => {
    setSearchElements(prev => ({ ...prev, product_name: productName }));
  }, []);

  const setType = useCallback((type: string | null) => {
    setSearchElements(prev => ({ ...prev, type }));
  }, []);

  const setProductCode = useCallback((product_code: string | null) => {
    setSearchElements(prev => ({ ...prev, product_code }));
  }, []);

  const resetSearchElement = useCallback(() => {
    setSearchElements(initialSearchState);
    setFilteredProducts(products);
  }, [products]);

  const updateSearchElement = useCallback((element: Partial<SearchElements>) => {
    setSearchElements(prev => ({ ...prev, ...element }));
  }, []);

  // 검색 실행 함수
  const performSearch = useCallback(() => {
    let filtered = [...products];
    
    if (searchElements.product_name && searchElements.product_name.trim() !== '') {
      filtered = filtered.filter(product => 
        product.name.toLowerCase().includes(searchElements.product_name!.toLowerCase())
      );
    }
    
    if (searchElements.type) {
      filtered = filtered.filter(product => 
        product.type.toLowerCase() === searchElements.type!.toLowerCase()
      );
    }
    
    if (searchElements.product_code) {
      filtered = filtered.filter(product => 
        product.code.toLowerCase().includes(searchElements.product_code!.toLowerCase())
      );
    }
    
    setFilteredProducts(filtered);
  }, [products, searchElements]);

  return {
    searchElements,
    setProductName,
    setType,
    setProductCode,
    resetSearchElement,
    updateSearchElement,
    products,
    filteredProducts,
    performSearch
  };
};

export default useSearch;