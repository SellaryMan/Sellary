'use client'
import { useState, useRef } from "react"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Search, X } from "lucide-react"
import useSearch from "@/hooks/useSearch"
// import { CreateProduct } from "@/components/outbound/CreateProduct"

// 추후에 제거
interface ShippedProductExp {
  id: number;
  expDate: string;
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
  shippedProductExp: ShippedProductExp[] | null;
  shippedProductCost: ShippedProductCost | null;
}

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
  
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null)

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

  const handleProductSelect = (product: Product) => {
    setSelectedProduct(product)
    console.log("Selected : ", product)
  }

  return (
    <div className="flex container gap-3">
      <div className="">
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
      {/* <CreateProduct/> */}
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
              <div
                key={product.id}
                className="flex items-center border p-2 rounded-md cursor-pointer hover:bg-gray-50"
                onClick={() => handleProductSelect(product)}>
                <div>
                  <p className="font-bold mx-2 text-xs min-w-20">{product.name}</p>

                </div>
                <div className="ml-4 min-w-32 min-h-6">
                  <span className="flex gap-1 text-[12px]">
                    <p className="font-bold min-w-10">바코드</p>
                    {product.barcode===null?"-":product.barcode}
                  </span>
                  <span className="flex gap-1 text-[12px]">
                  <p className="font-bold min-w-10">상품코드</p>
                    <p className="text-[12px]">{product.code}</p>
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
      <div className="w-1/2 border rounded-lg p-4 min-h-96">
        {selectedProduct ? (
          <div className="flex flex-col">
            <div className="flex">
            <h2 className="text-xl font-bold mb-4">{selectedProduct.name}</h2>
            {selectedProduct.tags && selectedProduct.tags.length > 0 && (
                  <div className="flex flex-wrap gap-2 ml-auto">
                    {selectedProduct.tags.map((tag: string, index: number) => (
                      <span key={index} className="flex flex-col justify-center h-7 bg-gray-100 px-2 py-1 rounded-md text-xs">
                        {tag}
                      </span>
                    ))}
                  </div>
              )}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="col-span-2">
                <h3 className="text-sm font-medium text-gray-500">기본 정보</h3>
                <div className="mt-2 space-y-2 [&>..]: text-xs">
                  <div className="flex justify-between">
                    <span className="font-medium">상품명</span>
                    <span>{selectedProduct.name}</span>
                  </div>
                  <div className="flex justify-between">
                    <span className="font-medium">상품유형</span>
                    <span>{selectedProduct.type}</span>
                  </div>
                  <div className="flex justify-between">
                    <span className="font-medium">상품코드</span>
                    <span>{selectedProduct.code}</span>
                  </div>
                  <div className="flex justify-between">
                    <span className="font-medium">바코드</span>
                    <span>{selectedProduct.barcode || "-"}</span>
                  </div>
                </div>
              </div>
              

              {selectedProduct.shippedProductCost && (
                <div className="col-span-2 mt-4">
                  <h3 className="text-sm font-medium text-gray-500">비용 정보</h3>
                  <div className="mt-2 grid grid-cols-2 gap-2 bg-gray-50 p-3 rounded [&>div]:text-xs">
                    <div>
                      <p className="text-xs text-gray-500">단위 구매가</p>
                      <p className="font-medium">{selectedProduct.shippedProductCost.unitPurchasePrice.toLocaleString()}원</p>
                    </div>
                    <div>
                      <p className="text-xs text-gray-500">박스 구매가</p>
                      <p className="font-medium">{selectedProduct.shippedProductCost.boxPurchasePrice.toLocaleString()}원</p>
                    </div>
                    <div>
                      <p className="text-xs text-gray-500">팔레트 구매가</p>
                      <p className="font-medium">{selectedProduct.shippedProductCost.palletPurchasePrice.toLocaleString()}원</p>
                    </div>
                    <div>
                      <p className="text-xs text-gray-500">단위 판매가</p>
                      <p className="font-medium">{selectedProduct.shippedProductCost.unitSellingPrice.toLocaleString()}원</p>
                    </div>
                    <div>
                      <p className="text-xs text-gray-500">박스 판매가</p>
                      <p className="font-medium">{selectedProduct.shippedProductCost.boxSellingPrice.toLocaleString()}원</p>
                    </div>
                    <div>
                      <p className="text-xs text-gray-500">팔레트 판매가</p>
                      <p className="font-medium">{selectedProduct.shippedProductCost.palletSellingPrice.toLocaleString()}원</p>
                    </div>
                  </div>
                </div>
              )}
              
              {selectedProduct.shippedProductExp && selectedProduct.shippedProductExp.length > 0 && (
                <div className="col-span-2 mt-2">
                  <h3 className="text-sm font-medium text-gray-500">유통기한 정보</h3>
                  <div className="mt-2 space-y-3 [&>div]:text-xs">
                    {selectedProduct.shippedProductExp.map((exp) => (
                      <div key={exp.id} className="bg-gray-50 p-3 rounded">
                        <div className="grid grid-cols-2 gap-2">
                          <div>
                            <p className="text-xs text-gray-500">유통기한</p>
                            <p className="font-medium">{exp.expDate.toString()}</p>
                          </div>
                          <div>
                              <p className="text-xs text-gray-500">제조일자</p>
                              <p className="font-medium">{exp.manufactureDate}</p>
                            </div>
                            <div>
                              <p className="text-xs text-gray-500">재고 부족 기준일</p>
                              <p className="font-medium">{exp.lowStockThresholdDay}일</p>
                            </div>
                            <div>
                              <p className="text-xs text-gray-500">출고 불가 기준일</p>
                              <p className="font-medium">{exp.noShippingThresholdDay}일</p>
                            </div>
                          <div>
                            <p className="text-xs text-gray-500">수량</p>
                            <p className="font-medium">{exp.quantity}</p>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          </div>
        ) : (
          <div className="flex items-center justify-center h-full text-gray-400">
            검색 결과에서 상품을 선택하면 상세 정보가 표시됩니다.
          </div>
        )}
      </div>
    </div>
  )
}