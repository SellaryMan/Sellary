'use client'
import { Input } from "@/components/ui/input"
import { Button } from "../ui/button"
import { useState } from "react"
import { Search, X } from "lucide-react"

interface TextSearchProps {
  inputValue: string;
  setInputValue: (value: string) => void;
}

const TextSearch = ({ inputValue, setInputValue }: TextSearchProps) => {
  const [isFocused, setIsFocused] = useState(false)
  
  const handleInputChange = (e) => {
    setInputValue(e.target.value)
  }
  
  const handleClearInput = () => {
    setInputValue("")
  }

  return (
    <div className="flex gap-1">
    <div className="relative flex w-72 gap-2">
      {isFocused && (
        <div className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500">
          <Search size={16} />
        </div>
      )}
      
      <Input 
        type="text"
        value={inputValue}
        onChange={handleInputChange}
        placeholder="이름으로 검색" 
        className={`px-2 w-full ${isFocused ? 'pl-8 pr-8' : 'pl-4'}`}
        onFocus={() => setIsFocused(true)}
        onBlur={() => setIsFocused(false)}
      />
      
      {isFocused && inputValue && (
        <div 
          className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500
          cursor-pointer"
          onClick={handleClearInput}
        >
          <X size={16} />
        </div>
      )}
    </div>
          <Button>
          검색
        </Button>
    </div>
  )
}

export default TextSearch;