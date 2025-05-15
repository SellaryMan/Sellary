import {
    Dialog,
    DialogContent,
    DialogTrigger,
    DialogOverlay,
    DialogHeader,
    DialogFooter,
    DialogTitle
  } from "@/components/ui/dialog"
import { Button } from "../ui/button"
import { Input } from "../ui/input"

export const CreateProduct = ()=>{
return(
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
                <label htmlFor="company" className="text-left">
                  회사명
                </label>
                <Input id="company" className="col-span-3" />
                <label htmlFor="supplier" className="text-left">
                  공급사
                </label>
                <Input id="supplier" className="col-span-3" />
                <label htmlFor="category" className="text-left">
                  구분
                </label>
                <Input id="category" className="col-span-3" />
              </div>
              <div className="grid grid-cols-12 items-center gap-4">
                <label htmlFor="productCode" className="text-left col-span-2">
                  출고상품코드
                </label>
                <Input id="productCode" className="col-span-2" />
                <label htmlFor="productName" className="text-left">
                  상품명
                </label>
                <Input id="productName" className="col-span-7" />
              </div>
              <div className="grid grid-cols-12 items-center gap-4">
              <label htmlFor="barcode" className="text-left">
                  바코드
                </label>
                <Input id="barcode" className="col-span-3" />
                <label htmlFor="keyword" className="text-left">
                  키워드
                </label>
                <Input id="keyword" className="col-span-7" />
              </div>
              <h2 className="mt-4">규격</h2>
              <h3>낱개규격</h3>
              <div className="grid grid-cols-12 items-center gap-4">
                <label htmlFor="width" className="text-left">
                  가로
                </label>
                <Input id="width" className="col-span-2" />
                <label htmlFor="length" className="text-left">
                  세로
                </label>
                <Input id="length" className="col-span-2" />
                <label htmlFor="height" className="text-left">
                  높이
                </label>
                <Input id="height" className="col-span-2" /><label htmlFor="weight" className="text-left">
                  무게
                </label>
                <Input id="weight" className="col-span-2" />
              </div>
              <h3>박스</h3>
              <div className="grid grid-cols-12 items-center gap-4">
                <label htmlFor="boxCount" className="text-left col-span-2">
                  박스개수
                </label>
                <Input id="boxCount" className="col-span-4" />
                <label htmlFor="paletteCount" className="text-left col-span-2">
                  팔레트개수
                </label>
                <Input id="paletteCount" className="col-span-4" />
              </div>
             
            </div>
            <div className="grid gap-1 py-4 [&_label,&_input]:text-[0.6rem] [&_label]:min-w-18 [&_input]:h-6 [&_h2]:text-[1rem] [&_h3]:text-[0.8rem]">
            <h2 className="mt-4">유통기한 관리</h2>
              <div className="grid grid-cols-15 items-center gap-4">
                <label htmlFor="lowStockThresholdDay" className="text-left col-span-3">
                  임박재고 전환 기준일
                </label>
                <Input id="lowStockThresholdDay" className="col-span-4" />
                <label htmlFor="noShippingThresholdDay" className="text-left col-span-3">
                  출고불가 기준일
                </label>
                <Input id="noShippingThresholdDay" className="col-span-3" />
              </div>
              <h2 className="mt-4">비용 관리</h2>
              <h3>매입가격</h3>
              <div className="grid grid-cols-16 items-center gap-4">
                <label htmlFor="unitPurchasePrice" className="text-left col-span-1">
                  낱개
                </label>
                <Input id="unitPurchasePrice" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label htmlFor="boxPurchasePrice" className="text-left col-span-1">
                  박스
                </label>
                <Input id="boxPurchasePrice" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label htmlFor="palettePurchasePrice" className="text-left col-span-1">
                  팔레트
                </label>
                <Input id="palettePurchasePrice" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
              </div>
              <h3>판매가격</h3>
              <div className="grid grid-cols-16 items-center gap-4">
              <label htmlFor="unitSellingPrice" className="text-left col-span-1">
                  낱개
                </label>
                <Input id="unitSellingPrice" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label htmlFor="boxSellingPrice" className="text-left col-span-1">
                  박스
                </label>
                <Input id="boxSellingPrice" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
                <label htmlFor="paletteSellingPrice" className="text-left col-span-1">
                  팔레트
                </label>
                <Input id="paletteSellingPrice" className="col-span-2" />
                <label className="text-left col-span-2">
                  개
                </label>
              </div>
              <h2 className="mt-4">보관위치</h2>
              <div className="grid grid-cols-16 items-center gap-4">
                <label htmlFor="storage" className="text-left col-span-1">
                  창고
                </label>
                <Input id="storage" className="col-span-4" />
                
                <label htmlFor="storageLocation" className="text-left col-span-1">
                  위치
                </label>
                <Input id="storageLocation" className="col-span-4" />
              </div>
            </div>
          </div>
          <DialogFooter>
            <Button>추가</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
)
}