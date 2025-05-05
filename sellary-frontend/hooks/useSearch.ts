'use client'

import { useState, useCallback } from "react"

interface SearchElements {
    product_name?: string | null;
    type?: string | null;
    product_code?: string | null;
    search_type? : 'id'|'name'|'type'|'code'|'multi_condition'| null;
}

const initialSearchState: SearchElements = {
    product_name: null,
    type: null,
    product_code: null,
    search_type : null
};

interface IUseSearch {
    searchElements: SearchElements;
    setProductName: (productName: string | null) => void;
    setType : (type: string|null) => void;
    setProductCode : (product_code : string|null) => void;
    resetSearchElement: () => void;
    updateSearchElement: (element: Partial<SearchElements>) => void;
}

const useSearch = (): IUseSearch => {
    const [searchElements, setSearchElements] = useState<SearchElements>(initialSearchState);
    
    const setProductName = useCallback((productName: string | null) => {
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
    }, []);

    const updateSearchElement = useCallback((element: Partial<SearchElements>) => {
        setSearchElements(prev => ({ ...prev, ...element }));
    }, []);

    return {
        searchElements,
        setProductName,
        setType,
        setProductCode,
        resetSearchElement,
        updateSearchElement
    };
};

export default useSearch;