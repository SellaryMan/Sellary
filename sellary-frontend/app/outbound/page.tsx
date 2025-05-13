'use client'
import { useState, useRef } from "react"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Search, X } from "lucide-react"
import {
  Dialog,
  DialogContent,
  DialogTrigger,
  DialogOverlay,
  DialogHeader,
  DialogFooter,
  DialogTitle
} from "@/components/ui/dialog"
import { Calendar } from "@/components/ui/calendar"
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover"
import useSearch from "@/hooks/useSearch"

export default function SearchPage() {
  const [isFocused, setIsFocused] = useState(false)
  const inputRef = useRef<HTMLInputElement | null>(null)
  const clearBtnRef = useRef<HTMLDivElement | null>(null)
  const [isClearing, setIsClearing] = useState(false)
  
  const {
    searchElements,
    setProductName,
    products,
    fetchProducts,
    isLoading,
    error
  } = useSearch()
  
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
    console.log("검색 실행: ", searchElements.product_name)
    if(searchElements.product_name && searchElements.product_name.length>0)fetchProducts('NAME')
    else fetchProducts()

  }

  return (
    <div className="container">
      <div className="mb-6">
        <form onSubmit={handleSubmit} className="flex gap-1">
          <div className="relative flex w-72 gap-4">
            {isFocused && (
              <div className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500">
                <Search size={16} />
              </div>
            )}
            
            <Input 
              ref={inputRef}
              type="text"
              value={searchElements.product_name || ""}
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
          <Button type="submit" className="w-22">
            검색
          </Button>
        </form>
      </div>

      <Dialog>
        <DialogOverlay className="z-999 bg-black/40"/>
        <DialogTrigger asChild>
          <Button>추가</Button>
        </DialogTrigger>
        <DialogContent className="min-w-240 z-1001 overflow-hidden">
          <DialogHeader>
            <DialogTitle className="text-md">
              출고상품 추가
            </DialogTitle>
          </DialogHeader>
          <div className="flex gap-12">
            <div className="grid gap-1 py-4 [&_label,&_input]:text-[0.6rem] [&_label]:min-w-18 [&_input]:h-6 [&_h2]:text-[1rem] [&_h3]:text-[0.8rem]">
              <h2>기본정보</h2>
              <div className="grid grid-cols-12 items-center gap-4">
                <label className="text-left">
                  회사명
                </label>
                <Input id="name" className="col-span-3" />
                <label className="text-left">
                  공급사
                </label>
                <Input id="name" className="col-span-3" />
                <label className="text-left">
                  구분
                </label>
                <Input id="expdate" className="col-span-3" />
              </div>
              <div className="grid grid-cols-12 items-center gap-4">
                <label className="text-left col-span-2">
                  출고상품코드
                </label>
                <Input id="quantity" className="col-span-2" />
                <label className="text-left">
                  상품명
                </label>
                <Input id="manufactureDate" className="col-span-7" />
              </div>
              <div className="grid grid-cols-12 items-center gap-4">
              <label className="text-left">
                  바코드
                </label>
                <Input id="barcode" className="col-span-3" />
                <label className="text-left">
                  키워드
                </label>
                <Input id="type" className="col-span-7" />
              </div>
              <h2 className="mt-4">규격</h2>
              <h3>낱개규격</h3>
              <div className="grid grid-cols-12 items-center gap-4">
                <label className="text-left">
                  가로
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left">
                  세로
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left">
                  높이
                </label>
                <Input id="keyword" className="col-span-2" /><label className="text-left">
                  무게
                </label>
                <Input id="keyword" className="col-span-2" />
              </div>
              <h3>박스</h3>
              <div className="grid grid-cols-12 items-center gap-4">
                <label className="text-left col-span-2">
                  박스개수
                </label>
                <Input id="keyword" className="col-span-4" />
                <label className="text-left col-span-2">
                  팔레트개수
                </label>
                <Input id="keyword" className="col-span-4" />
              </div>
             
            </div>
            <div className="grid gap-1 py-4 [&_label,&_input]:text-[0.6rem] [&_label]:min-w-18 [&_input]:h-6 [&_h2]:text-[1rem] [&_h3]:text-[0.8rem]">
            <h2 className="mt-4">유통기한 관리</h2>
              <div className="grid grid-cols-15 items-center gap-4">
                <label className="text-left col-span-3">
                  임박재고 전환 기준일
                </label>
                <Input id="keyword" className="col-span-4" />
                <label className="text-left col-span-3">
                  출고불가 기준일
                </label>
                <Input id="keyword" className="col-span-3" />
              </div>
              <h2 className="mt-4">비용 관리</h2>
              <h3>매입가격</h3>
              <div className="grid grid-cols-16 items-center gap-4">
                <label className="text-left col-span-1">
                  낱개
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label className="text-left col-span-1">
                  박스
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label className="text-left col-span-1">
                  팔레트
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
              </div>
              <h3>판매가격</h3>
              <div className="grid grid-cols-16 items-center gap-4">
              <label className="text-left col-span-1">
                  낱개
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label className="text-left col-span-1">
                  박스
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label className="text-left col-span-1">
                  팔레트
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
              </div>
              <h2 className="mt-4">보관위치</h2>
              <div className="grid grid-cols-16 items-center gap-4">
              <label className="text-left col-span-1">
                  낱개
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label className="text-left col-span-1">
                  박스
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label className="text-left col-span-1">
                  팔레트
                </label>
                <Input id="keyword" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
              </div>
            </div>
          </div>
          <DialogFooter>
            <Button>추가</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      <div className="w-96">

      {isLoading && (
        <div className="text-center text-gray-500 my-8">
          데이터를 불러오는 중...
        </div>
      )}
      
      {error && (
        <div className="text-center text-red-500 my-8">
          오류가 발생했습니다!
        </div>
      )}
      
      {!isLoading && !error && products.length === 0 && (
        <div className="text-center text-gray-500 my-8">
          검색 결과가 없습니다.
        </div>
      )}
      
      {!isLoading && !error && products.length > 0 && (
        <div className="mt-4">
          <div className="flex flex-col gap-2">
          <div className="flex w-full text-end text-xs mb-2 ml-1">{products.length}개의 검색결과</div>
            {products.map(product => (
              <div key={product.id} className="flex items-center border p-2 rounded-md">
                <div>
                  <p className="font-bold text-xs">{product.name}</p>
                  <p className="text-[12px]">{product.code}</p>
                </div>
                <div className="ml-4 min-w-32 min-h-10">
                  <span className="flex gap-1 text-[12px]">
                    <p className="font-bold">재고</p>
                    {product.shippedProductExp[0].expDate===null?"-":product.shippedProductExp[0].quantity}
                  </span>
                  <span className="flex gap-1 text-[12px]">
                    <p className="font-bold">바코드</p>
                    {product.barcode===null?"-":product.barcode}
                  </span>
                  <span className="flex gap-1 text-[12px]">
                    <p className="font-bold">유통기한</p>
                    {product.shippedProductExp[0].expDate===null? "-":product.shippedProductExp[0].expDate}
                  </span>
                </div>

                <div className="ml-4 min-w-16 min-h-10">
                  <span className="flex gap-1 text-[12px]">
                    <p className="font-bold">구매가격</p>
                    {product.shippedProductCost?.unitPurchasePrice===null?"-":product.shippedProductExp[0].quantity}
                    <p className="font-bold">원</p>
                  </span>
                  <span className="flex gap-1 text-[12px]">
                    <p className="font-bold">판매가격</p>
                    {product.shippedProductCost?.unitSellingPrice===null?"-":product.shippedProductCost?.unitSellingPrice}
                    <p className="font-bold">원</p>
                  </span>
                  <span className="flex gap-1 text-[12px]">
                    
                  </span>
                </div>
                <div className="flex items-center gap-2 ml-auto h-3">
                  {product.tags && product.tags.map((tag, index) => (
                    <p key={index} className="bg-gray-100 px-2 py-1 rounded text-[14px]">{tag}</p>
                  ))}
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
      </div>
    </div>
  )
}