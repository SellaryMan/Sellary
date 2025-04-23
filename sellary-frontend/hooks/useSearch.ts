'use client'

import { useState, useCallback } from "react"

interface SearchElements {
    company_name?: string | null;
    product_name?: string | null;
    supplier?: string | null;
    type?: string | null;
    product_code?: string | null;
    barcode?: string | null;
    purchase_price?: {
        min_price?: number | null;
        max_price?: number | null;
    };
    keywords?: Array<string> | null;
    exp?: Date | null;
    quantity?: {
        min_quantity?: number | null;
        max_quantity?: number | null;
    } | null;
    page_size : number;
    page_idx : number;
}

const initialSearchState: SearchElements = {
    company_name: null,
    product_name: null,
    supplier: null,
    type: null,
    product_code: null,
    barcode: null,
    purchase_price: {
        max_price : null,
        min_price : null
    },
    keywords: null,
    exp: null,
    quantity: null,
    page_size: 10,
    page_idx: 1
};

interface IUseSearch {
    searchElements: SearchElements;
    setCompanyName: (companyName: string | null) => void;
    setproductName: (productName: string | null) => void;
    setSupplier : (supplier : string|null) => void;
    setType : (type: string|null) => void;
    setProductCode : (product_code : string|null) => void;
    setBarCode : (barcode : string|null)=>void;
    setMinPrice: (min_price: number) => void;
    setMaxPrice: (max_price: number) => void;
    setKeywords : (keywords : string[])=>void;
    setExp : (exp :Date | null)=>void;
    setQuantity : (quantity : number|null) => void;
    setPageIdx: (pageIdx: number) => void;
    setPageSize: (pageSize: number) => void;
    resetSearchElement: () => void;
    updateSearchElement: (element: Partial<SearchElements>) => void;
}

const useSearch = (): IUseSearch => {
    const [searchElements, setSearchElements] = useState<SearchElements>(initialSearchState);
    
    console.log(searchElements.purchase_price);
    // key를 제네릭으로 해서  모듈화 잊지말자
    const setCompanyName = useCallback((companyName: string | null) => {
        setSearchElements(prev => ({ ...prev, company_name: companyName }));
    }, []);

    const setproductName = useCallback((productName: string | null) => {
        setSearchElements(prev => ({ ...prev, product_name: productName }));
    }, []);

    const setSupplier = useCallback((supplier: string | null) => {
        setSearchElements(prev => ({ ...prev, supplier }));
    }, []);

    const setType = useCallback((type: string | null) => {
        setSearchElements(prev => ({ ...prev, type }));
    }, []);

    const setProductCode = useCallback((product_code: string | null) => {
        setSearchElements(prev => ({ ...prev, product_code }));
    }, []);

    const setBarCode = useCallback((barcode: string | null) => {
        setSearchElements(prev => ({ ...prev, barcode }));
    }, []);

    const setMinPrice = useCallback((minPrice: number) => {
        setSearchElements(prev => ({
        ...prev,
        purchase_price: {
            ...(prev.purchase_price || {}),
            min_price: minPrice
        }
        }));
    }, []);
    
    const setMaxPrice = useCallback((maxPrice: number) => {
        setSearchElements(prev => ({
        ...prev,
        purchase_price: {
            ...(prev.purchase_price || {}),
            max_price: maxPrice
        }
        }));
    }, []);

    const setKeywords = useCallback((keywords: string[]) => {
        setSearchElements(prev => ({ ...prev, keywords }));
    }, []);

    const setExp = useCallback((exp: Date | null) => {
        setSearchElements(prev => ({ ...prev, exp }));
    }, []);

    const setQuantity = useCallback((quantity: number | null) => {
        setSearchElements(prev => ({ 
            ...prev, 
            quantity: quantity ? {
                min_quantity: quantity,
                max_quantity: null
            } : null 
        }));
    }, []);

    const setPageIdx = useCallback((pageIdx: number) => {
        setSearchElements(prev => ({ ...prev, page_idx: pageIdx }));
    }, []);

    const setPageSize = useCallback((pageSize: number) => {
        setSearchElements(prev => ({ ...prev, page_size: pageSize }));
    }, []);

    const resetSearchElement = useCallback(() => {
        setSearchElements(initialSearchState);
    }, []);

    const updateSearchElement = useCallback((element: Partial<SearchElements>) => {
        setSearchElements(prev => ({ ...prev, ...element }));
    }, []);

    return {
        searchElements,
        setCompanyName,
        setproductName,
        setSupplier,
        setType,
        setProductCode,
        setBarCode,
        setMinPrice,
        setMaxPrice,
        setKeywords,
        setExp,
        setQuantity,
        setPageIdx,
        setPageSize,
        resetSearchElement,
        updateSearchElement
    };
};

export default useSearch;