'use client'
import { useState, useCallback, useEffect } from "react"

interface ShippedProductExp {
  id: number;
  expDate: String;
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
    fetchProducts: (searchType?: string) => void;
    isLoading: boolean;
    error: string | null;
}

const useSearch = (): IUseSearch => {
    const [searchElements, setSearchElements] = useState<SearchElements>(initialSearchState);
    const [products, setProducts] = useState<Product[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);
  
  useEffect(() => {
    const initialLoad = async () => {
      await fetchProducts();
    };
    initialLoad();
  }, []);

  const fetchProducts = useCallback(async (searchType?: string) => {
    setIsLoading(true);
    setError(null);
    
    try {
      const queryParams: Record<string, string> = {};
      
      if (searchElements.product_name && searchElements.product_name.trim() !== '') {
        queryParams.name = searchElements.product_name;
      }
      
      if (searchType) queryParams.queryType = searchType;
      
      if (searchElements.product_code) {
        queryParams.code = searchElements.product_code;
      }
      
      const queryString = new URLSearchParams(queryParams).toString();

      if (!process.env.NEXT_PUBLIC_API_KEY) {
        throw new Error("no API KEY");
      }

      const response = await fetch(queryString 
        ? `${process.env.NEXT_PUBLIC_API_KEY}/shipped-product?${queryString}`
        : process.env.NEXT_PUBLIC_API_KEY+"/shipped-product");
      
      if (!response.ok) {
        throw new Error(`ERROR : ${response.status}`);
      }  
      const data = await response.json();
      console.log("fetch data : " ,data)
      setProducts(data);
    } catch (err) {
      setError("ERROR");
    } finally {
      setIsLoading(false);
    }
  }, [searchElements]);

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
    fetchProducts();
  }, [fetchProducts]);

  const updateSearchElement = useCallback((element: Partial<SearchElements>) => {
    setSearchElements(prev => ({ ...prev, ...element }));
  }, []);

  return {
    searchElements,
    setProductName,
    setType,
    setProductCode,
    resetSearchElement,
    updateSearchElement,
    products,
    isLoading,
    fetchProducts,
    error
  };
};

export default useSearch;