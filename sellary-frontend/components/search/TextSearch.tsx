'use client'
import { Input } from "@/components/ui/input"
import { Button } from "../ui/button"
import { useState, useRef, useEffect } from "react"
import { Search, X } from "lucide-react"
import useSearch from "@/hooks/useSearch"

const TextSearch = () => {
  const [isFocused, setIsFocused] = useState(false)
  const {
    searchElements, 
    setProductName, 
    filteredProducts,
    performSearch
  } = useSearch()
  
  const inputRef = useRef<HTMLInputElement | null>(null)
  const clearBtnRef = useRef<HTMLDivElement | null>(null)
  const [isClearing, setIsClearing] = useState(false)
  
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setProductName(e.target.value)
  }
  
  const handleClearInput = () => {
    setIsClearing(true)
    setProductName("")
    setTimeout(() => {
      if (inputRef.current) {
        inputRef.current.focus()
      }
      setIsClearing(false)
    }, 10)
  }
  
  const handleBlur = (e: React.FocusEvent<HTMLInputElement>) => {
    if (isClearing || (clearBtnRef.current && clearBtnRef.current.contains(e.relatedTarget as Node))) {
      return
    }
    setIsFocused(false)
  }
  
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    performSearch()
  }

  const renderSearchResults = () => {
    if (filteredProducts.length === 0) {
      return <div className="mt-4 text-center text-gray-500">검색 결과가 없습니다.</div>
    }

    return (
      <div className="mt-4">
        <div className="flex flex-col">
          {filteredProducts.map(product => (
            <div key={product.id} className="border p-4 rounded-lg">
              <p className="font-bold">{product.name}</p>
              <p className="text-sm">코드: {product.code}</p>
              <p className="text-sm">타입: {product.type}</p>
            </div>
          ))}
        </div>
      </div>
    )
  }

  return (
    <div>
      <form onSubmit={handleSubmit} className="flex gap-1">
        <div className="relative flex w-72 gap-2">
          {isFocused && (
            <div className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500">
              <Search size={16} />
            </div>
          )}
          
          <Input 
            ref={inputRef}
            type="text"
            value={searchElements.product_name}
            onChange={handleInputChange}
            placeholder="이름으로 검색" 
            className={`px-2 w-full ${isFocused ? 'pl-8 pr-8' : 'pl-4'}`}
            onFocus={() => setIsFocused(true)}
            onBlur={handleBlur}
          />
          
          {isFocused && searchElements.product_name && (
            <div 
              ref={clearBtnRef}
              className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500
              cursor-pointer"
              onClick={handleClearInput}
              tabIndex={-1}
            >
              <X size={16} />
            </div>
          )}
        </div>
        <Button type="submit">
          검색
        </Button>
      </form>

      {renderSearchResults()}
    </div>
  )
}

export default TextSearch;